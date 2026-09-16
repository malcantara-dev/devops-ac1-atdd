package org.example.grupo_7_praticaatdd.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "assinaturas")
public class Assinatura {

    public static final String MENSAGEM_CREDITOS_INSUFICIENTES = "Creditos insuficientes";

    public static final int CREDITOS_POR_CONCLUSAO = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // EnumType.STRING grava o nome do enum no banco (BASICO / PREMIUM).
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanoAssinatura plano = PlanoAssinatura.BASICO;

    @Column(nullable = false)
    private int creditosCursos = 0;

    @Column(nullable = false)
    private int cursosConcluidosComSucesso = 0;

    public Long getId() {
        return id;
    }

    public PlanoAssinatura getPlano() {
        return plano;
    }

    public int getCreditosCursos() {
        return creditosCursos;
    }

    public int getCursosConcluidosComSucesso() {
        return cursosConcluidosComSucesso;
    }

    public void adicionarCreditos(int quantidade) {
        this.creditosCursos = this.creditosCursos + quantidade;
    }

    public void registrarConclusaoComSucesso() {
        this.cursosConcluidosComSucesso = this.cursosConcluidosComSucesso + 1;
        adicionarCreditos(CREDITOS_POR_CONCLUSAO);
    }

    public void consumirCredito() {
        if (this.creditosCursos < 1) {
            throw new IllegalStateException(MENSAGEM_CREDITOS_INSUFICIENTES);
        }
        this.creditosCursos = this.creditosCursos - 1;
    }
}
