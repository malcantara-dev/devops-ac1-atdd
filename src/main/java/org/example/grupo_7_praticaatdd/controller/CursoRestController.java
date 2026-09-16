package org.example.grupo_7_praticaatdd.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.grupo_7_praticaatdd.dto.CursoRequestDTO;
import org.example.grupo_7_praticaatdd.dto.CursoResponseDTO;
import org.example.grupo_7_praticaatdd.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@Tag(name = "Cursos")
public class CursoRestController {

    private final CursoService service;

    public CursoRestController(CursoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar curso")
    public CursoResponseDTO criar(@Valid @RequestBody CursoRequestDTO dto) {
        return service.criar(dto.getTitulo());
    }

    @GetMapping
    @Operation(summary = "Listar cursos")
    public List<CursoResponseDTO> listar() {
        return service.listar();
    }
}
