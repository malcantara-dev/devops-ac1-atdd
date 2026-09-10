package org.example.grupo_7_praticaatdd.domain;

public class Assinatura {

    private int creditosCursos = 0;
    private int cursosConcluidosComSucesso = 0;

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
        adicionarCreditos(3);
    }

    // TODO Henrique - cenarios 5 e 6
    public void consumirCredito() {
    }
}