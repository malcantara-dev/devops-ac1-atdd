package org.example.grupo_7_praticaatdd.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.grupo_7_praticaatdd.dto.MatriculaRequestDTO;
import org.example.grupo_7_praticaatdd.dto.MatriculaResponseDTO;
import org.example.grupo_7_praticaatdd.dto.NotaRequestDTO;
import org.example.grupo_7_praticaatdd.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@Tag(name = "Matriculas")
public class MatriculaRestController {

    private final MatriculaService service;

    public MatriculaRestController(MatriculaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Matricular usuario em um curso do plano")
    public MatriculaResponseDTO matricular(@Valid @RequestBody MatriculaRequestDTO dto) {
        return service.matricular(dto.getUsuarioId(), dto.getCursoId());
    }

    @PostMapping("/bonus")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Desbloquear curso bonus consumindo um credito")
    public MatriculaResponseDTO desbloquear(@Valid @RequestBody MatriculaRequestDTO dto) {
        return service.desbloquear(dto.getUsuarioId(), dto.getCursoId());
    }

    @PutMapping("/{id}/concluir")
    @Operation(summary = "Concluir matricula com a nota final")
    public MatriculaResponseDTO concluir(@PathVariable Long id, @Valid @RequestBody NotaRequestDTO dto) {
        return service.concluir(id, dto.getNota());
    }

    @PutMapping("/{id}/nota-parcial")
    @Operation(summary = "Registrar nota parcial sem concluir a matricula")
    public MatriculaResponseDTO registrarNotaParcial(@PathVariable Long id, @Valid @RequestBody NotaRequestDTO dto) {
        return service.registrarNotaParcial(id, dto.getNota());
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar matriculas de um usuario")
    public List<MatriculaResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }
}
