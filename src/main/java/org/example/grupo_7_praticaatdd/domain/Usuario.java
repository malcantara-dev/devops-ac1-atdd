package org.example.grupo_7_praticaatdd.domain;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private final String nome;
    private final Assinatura assinatura;
    private final List<Matricula> matriculas = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
        this.assinatura = new Assinatura();
    }

    public String getNome() {
        return nome;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void adicionarMatricula(Matricula matricula) {
        this.matriculas.add(matricula);
    }

    // TODO Alcantara - cenarios 1 e 2
    public void concluirCurso(Matricula matricula, double notaFinal) {
    }

    // TODO Rafael - cenario 3
    public Matricula matricularEm(Curso curso) {
        Matricula matricula = new Matricula(this, curso, false);
        adicionarMatricula(matricula);
        return matricula;
    }

    // TODO Henrique - cenarios 5 e 6
    public void desbloquearCurso(Curso curso) {
    }
}