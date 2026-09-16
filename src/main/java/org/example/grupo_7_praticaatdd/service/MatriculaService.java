package org.example.grupo_7_praticaatdd.service;

import org.example.grupo_7_praticaatdd.domain.Assinatura;
import org.example.grupo_7_praticaatdd.domain.Curso;
import org.example.grupo_7_praticaatdd.domain.Matricula;
import org.example.grupo_7_praticaatdd.domain.Usuario;
import org.example.grupo_7_praticaatdd.dto.MatriculaResponseDTO;
import org.example.grupo_7_praticaatdd.entity.CursoEntity;
import org.example.grupo_7_praticaatdd.entity.MatriculaEntity;
import org.example.grupo_7_praticaatdd.entity.UsuarioEntity;
import org.example.grupo_7_praticaatdd.repository.CursoRepository;
import org.example.grupo_7_praticaatdd.repository.MatriculaRepository;
import org.example.grupo_7_praticaatdd.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Camada SERVICE: orquestra repositorios e delega as regras de negocio
// para as classes do pacote domain, que foram escritas via TDD.
@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository,
                            UsuarioRepository usuarioRepository,
                            CursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<MatriculaResponseDTO> listarPorUsuario(Long usuarioId) {
        return matriculaRepository.findByUsuarioId(usuarioId).stream().map(this::toDTO).toList();
    }

    @Transactional
    public MatriculaResponseDTO matricular(Long usuarioId, Long cursoId) {
        MatriculaEntity matricula = new MatriculaEntity(buscarUsuario(usuarioId), buscarCurso(cursoId), false);
        return toDTO(matriculaRepository.save(matricula));
    }

    // Cenarios 5 e 6: o desbloqueio so acontece se a assinatura tiver credito.
    @Transactional
    public MatriculaResponseDTO desbloquear(Long usuarioId, Long cursoId) {
        UsuarioEntity usuario = buscarUsuario(usuarioId);

        Assinatura assinatura = assinaturaDoDominio(usuario);
        assinatura.consumirCredito();
        usuario.setCreditosCursos(assinatura.getCreditosCursos());

        MatriculaEntity matricula = new MatriculaEntity(usuario, buscarCurso(cursoId), true);
        return toDTO(matriculaRepository.save(matricula));
    }

    // Cenarios 1 e 2: concluir com nota >= 7 rende 3 creditos, abaixo disso nao rende nada.
    @Transactional
    public MatriculaResponseDTO concluir(Long matriculaId, Double nota) {
        MatriculaEntity entity = buscarMatricula(matriculaId);

        Matricula matricula = matriculaDoDominio(entity);
        matricula.concluir(nota);

        entity.setStatus(matricula.getStatus());
        entity.setNotaFinal(matricula.getNotaFinal());

        if (matricula.concluidoComAproveitamento()) {
            creditarConclusao(entity.getUsuario());
        }
        return toDTO(matriculaRepository.save(entity));
    }

    // Cenario 4: a nota parcial e apenas registrada, o curso segue em andamento.
    @Transactional
    public MatriculaResponseDTO registrarNotaParcial(Long matriculaId, Double nota) {
        MatriculaEntity entity = buscarMatricula(matriculaId);

        Matricula matricula = matriculaDoDominio(entity);
        matricula.registrarNotaParcial(nota);

        entity.setNotaParcial(matricula.getNotaParcial());
        return toDTO(matriculaRepository.save(entity));
    }

    private void creditarConclusao(UsuarioEntity usuario) {
        Assinatura assinatura = assinaturaDoDominio(usuario);
        assinatura.registrarConclusaoComSucesso();

        usuario.setCreditosCursos(assinatura.getCreditosCursos());
        usuario.setCursosConcluidosComSucesso(
                usuario.getCursosConcluidosComSucesso() + assinatura.getCursosConcluidosComSucesso());
    }

    private Assinatura assinaturaDoDominio(UsuarioEntity usuario) {
        Assinatura assinatura = new Assinatura();
        assinatura.adicionarCreditos(usuario.getCreditosCursos());
        return assinatura;
    }

    private Matricula matriculaDoDominio(MatriculaEntity entity) {
        Usuario usuario = new Usuario(entity.getUsuario().getNome());
        Curso curso = new Curso(entity.getCurso().getTitulo());
        return new Matricula(usuario, curso, entity.isBonus());
    }

    private UsuarioEntity buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado"));
    }

    private CursoEntity buscarCurso(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso nao encontrado"));
    }

    private MatriculaEntity buscarMatricula(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula nao encontrada"));
    }

    private MatriculaResponseDTO toDTO(MatriculaEntity entity) {
        return new MatriculaResponseDTO(
                entity.getId(),
                entity.getUsuario().getId(),
                entity.getCurso().getTitulo(),
                entity.getStatus().name(),
                entity.getNotaFinal(),
                entity.getNotaParcial(),
                entity.isBonus()
        );
    }
}
