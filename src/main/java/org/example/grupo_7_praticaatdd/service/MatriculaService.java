package org.example.grupo_7_praticaatdd.service;

import org.example.grupo_7_praticaatdd.domain.Curso;
import org.example.grupo_7_praticaatdd.domain.Matricula;
import org.example.grupo_7_praticaatdd.domain.Usuario;
import org.example.grupo_7_praticaatdd.dto.MatriculaResponseDTO;
import org.example.grupo_7_praticaatdd.repository.CursoRepository;
import org.example.grupo_7_praticaatdd.repository.MatriculaRepository;
import org.example.grupo_7_praticaatdd.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Este service e o que expoe, via API, as mesmas regras que foram criadas no TDD.
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
    public MatriculaResponseDTO matricular(Long usuarioId, Long cursoId, boolean bonus) {
        Usuario usuario = buscarUsuario(usuarioId);
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new IllegalArgumentException("Curso nao encontrado"));

        // A decisao de consumir ou nao o credito ja esta no dominio.
        Matricula matricula = bonus
                ? usuario.desbloquearCurso(curso)
                : usuario.matricularEm(curso);

        return toDTO(matriculaRepository.save(matricula));
    }

    @Transactional
    public MatriculaResponseDTO concluir(Long matriculaId, double notaFinal) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new IllegalArgumentException("Matricula nao encontrada"));

        // Creditar o usuario quando a nota e suficiente e regra do dominio.
        matricula.getUsuario().concluirCurso(matricula, notaFinal);

        return toDTO(matriculaRepository.save(matricula));
    }

    private Usuario buscarUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado"));
    }

    private MatriculaResponseDTO toDTO(Matricula matricula) {
        return new MatriculaResponseDTO(
                matricula.getId(),
                matricula.getUsuario().getId(),
                matricula.getUsuario().getNome(),
                matricula.getCurso().getId(),
                matricula.getCurso().getTitulo(),
                matricula.getStatus().name(),
                matricula.getNotaFinal(),
                matricula.isBonus()
        );
    }
}
