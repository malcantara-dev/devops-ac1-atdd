package org.example.grupo_7_praticaatdd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Camada ENTITY: guarda o estado do usuario no banco.
// As regras de negocio ficam no pacote domain, que e o codigo nascido do TDD.
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int creditosCursos;

    @Column(nullable = false)
    private int cursosConcluidosComSucesso;

    protected UsuarioEntity() {
    }

    public UsuarioEntity(String nome) {
        this.nome = nome;
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

    public void setCreditosCursos(int creditosCursos) {
        this.creditosCursos = creditosCursos;
    }

    public void setCursosConcluidosComSucesso(int cursosConcluidosComSucesso) {
        this.cursosConcluidosComSucesso = cursosConcluidosComSucesso;
    }
}
