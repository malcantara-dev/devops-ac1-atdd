package org.example.grupo_7_praticaatdd.service;

import org.example.grupo_7_praticaatdd.dto.UsuarioResponseDTO;
import org.example.grupo_7_praticaatdd.entity.UsuarioEntity;
import org.example.grupo_7_praticaatdd.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO criar(String nome) {
        return toDTO(usuarioRepository.save(new UsuarioEntity(nome)));
    }

    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll().stream().map(this::toDTO).toList();
    }

    private UsuarioResponseDTO toDTO(UsuarioEntity entity) {
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCreditosCursos(),
                entity.getCursosConcluidosComSucesso()
        );
    }
}
