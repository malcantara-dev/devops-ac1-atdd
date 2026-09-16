package org.example.grupo_7_praticaatdd.service;

import org.example.grupo_7_praticaatdd.domain.Usuario;
import org.example.grupo_7_praticaatdd.dto.UsuarioRequestDTO;
import org.example.grupo_7_praticaatdd.dto.UsuarioResponseDTO;
import org.example.grupo_7_praticaatdd.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Camada SERVICE: concentra a regra de negocio e orquestra dominio + repository.
@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    // Injecao de dependencia por construtor: quem cria os objetos e o Spring.
    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado"));
        return toDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        String emailNormalizado = dto.getEmail() == null ? null : dto.getEmail().trim().toLowerCase();

        if (repository.existsByEmailValor(emailNormalizado)) {
            throw new IllegalArgumentException("E-mail ja cadastrado");
        }

        // A senha e criptografada aqui, nunca no controller.
        Usuario usuario = new Usuario(
                dto.getNome(),
                emailNormalizado,
                passwordEncoder.encode(dto.getSenha())
        );

        return toDTO(repository.save(usuario));
    }

    private UsuarioResponseDTO toDTO(Usuario usuario) {
        var assinatura = usuario.getAssinatura();
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                assinatura.getPlano().name(),
                assinatura.getCreditosCursos(),
                assinatura.getCursosConcluidosComSucesso()
        );
    }
}
