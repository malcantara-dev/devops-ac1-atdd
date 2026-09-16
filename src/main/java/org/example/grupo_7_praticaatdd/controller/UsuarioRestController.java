package org.example.grupo_7_praticaatdd.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.grupo_7_praticaatdd.dto.UsuarioRequestDTO;
import org.example.grupo_7_praticaatdd.dto.UsuarioResponseDTO;
import org.example.grupo_7_praticaatdd.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios")
public class UsuarioRestController {

    private final UsuarioService service;

    public UsuarioRestController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar usuario")
    public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioRequestDTO dto) {
        return service.criar(dto.getNome());
    }

    @GetMapping
    @Operation(summary = "Listar usuarios com creditos e cursos concluidos")
    public List<UsuarioResponseDTO> listar() {
        return service.listar();
    }
}
