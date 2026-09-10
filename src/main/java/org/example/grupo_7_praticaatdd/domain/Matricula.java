package org.example.grupo_7_praticaatdd.domain;

public class Matricula {

    private final Usuario usuario;
    private final Curso curso;
    private final boolean bonus;

    private StatusMatricula status;
    private double notaFinal;
    private double notaParcial;

    public Matricula(Usuario usuario, Curso curso, boolean bonus) {
        this.usuario = usuario;
        this.curso = curso;
        this.bonus = bonus;
        this.status = StatusMatricula.EM_ANDAMENTO;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public boolean isBonus() {
        return bonus;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public double getNotaParcial() {
        return notaParcial;
    }

    // TODO Alcantara - cenarios 1 e 2
    public void concluir(double notaFinal) {
    }

    // TODO Alcantara - cenarios 1 e 2
    public boolean concluidoComAproveitamento() {
        return false;
    }

    // TODO Rafael - cenario 4
    public void registrarNotaParcial(double nota) {
        this.notaParcial = nota;
    }
}