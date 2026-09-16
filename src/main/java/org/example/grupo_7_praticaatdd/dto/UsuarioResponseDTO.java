package org.example.grupo_7_praticaatdd.dto;

public class UsuarioResponseDTO {

    private final Long id;
    private final String nome;
    private final int creditosCursos;
    private final int cursosConcluidosComSucesso;

    public UsuarioResponseDTO(Long id, String nome, int creditosCursos, int cursosConcluidosComSucesso) {
        this.id = id;
        this.nome = nome;
        this.creditosCursos = creditosCursos;
        this.cursosConcluidosComSucesso = cursosConcluidosComSucesso;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCreditosCursos() {
        return creditosCursos;
    }

    public int getCursosConcluidosComSucesso() {
        return cursosConcluidosComSucesso;
    }
}
