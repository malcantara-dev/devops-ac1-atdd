package org.example.grupo_7_praticaatdd.domain;

public class Matricula {

    public static final double NOTA_MINIMA_APROVACAO = 7.0;

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

    public void concluir(double notaFinal) {
        this.notaFinal = notaFinal;
        this.status = StatusMatricula.CONCLUIDO;
    }

    public boolean concluidoComAproveitamento() {
        return this.status == StatusMatricula.CONCLUIDO
                && this.notaFinal >= NOTA_MINIMA_APROVACAO;
    }

    // TODO Rafael - cenario 4
    public void registrarNotaParcial(double nota) {
    }
}