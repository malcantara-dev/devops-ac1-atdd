package org.example.grupo_7_praticaatdd.dto;

public class CursoResponseDTO {

    private final Long id;
    private final String titulo;

    public CursoResponseDTO(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
