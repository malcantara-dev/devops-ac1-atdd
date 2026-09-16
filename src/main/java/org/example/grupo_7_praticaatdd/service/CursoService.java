package org.example.grupo_7_praticaatdd.service;

import org.example.grupo_7_praticaatdd.dto.CursoResponseDTO;
import org.example.grupo_7_praticaatdd.entity.CursoEntity;
import org.example.grupo_7_praticaatdd.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public CursoResponseDTO criar(String titulo) {
        return toDTO(cursoRepository.save(new CursoEntity(titulo)));
    }

    public List<CursoResponseDTO> listar() {
        return cursoRepository.findAll().stream().map(this::toDTO).toList();
    }

    private CursoResponseDTO toDTO(CursoEntity entity) {
        return new CursoResponseDTO(entity.getId(), entity.getTitulo());
    }
}
