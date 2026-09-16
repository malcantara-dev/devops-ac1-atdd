package org.example.grupo_7_praticaatdd.dto;

// DTO de saida: repare que a senha nunca e devolvida para o cliente.
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String plano;
    private int creditosCursos;
    private int cursosConcluidosComSucesso;

    public UsuarioResponseDTO(Long id, String nome, String email, String plano,
                              int creditosCursos, int cursosConcluidosComSucesso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.plano = plano;
        this.creditosCursos = creditosCursos;
        this.cursosConcluidosComSucesso = cursosConcluidosComSucesso;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPlano() {
        return plano;
    }

    public int getCreditosCursos() {
        return creditosCursos;
    }

    public int getCursosConcluidosComSucesso() {
        return cursosConcluidosComSucesso;
    }
}
