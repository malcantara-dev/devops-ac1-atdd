package org.example.grupo_7_praticaatdd.service;

import org.example.grupo_7_praticaatdd.domain.Curso;
import org.example.grupo_7_praticaatdd.dto.CursoRequestDTO;
import org.example.grupo_7_praticaatdd.dto.CursoResponseDTO;
import org.example.grupo_7_praticaatdd.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<CursoResponseDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional
    public CursoResponseDTO criar(CursoRequestDTO dto) {
        Curso curso = new Curso(dto.getTitulo(), dto.getDescricao());
        return toDTO(repository.save(curso));
    }

    private CursoResponseDTO toDTO(Curso curso) {
        return new CursoResponseDTO(curso.getId(), curso.getTitulo(), curso.getDescricao());
    }
}
