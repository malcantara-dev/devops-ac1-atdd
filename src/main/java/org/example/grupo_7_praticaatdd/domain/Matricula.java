package org.example.grupo_7_praticaatdd.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matriculas")
public class Matricula {

    public static final double NOTA_MINIMA_APROVACAO = 7.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // fetch LAZY carrega o usuario apenas quando ele for realmente acessado.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Column(nullable = false)
    private boolean bonus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMatricula status;

    @Column(nullable = false)
    private double notaFinal;

    @Column(nullable = false)
    private double notaParcial;

    protected Matricula() {
    }

    public Matricula(Usuario usuario, Curso curso, boolean bonus) {
        this.usuario = usuario;
        this.curso = curso;
        this.bonus = bonus;
        this.status = StatusMatricula.EM_ANDAMENTO;
    }

    public Long getId() {
        return id;
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

    public void registrarNotaParcial(double nota) {
        this.notaParcial = nota;
    }

    public boolean estaEmAndamento() {
        return this.status == StatusMatricula.EM_ANDAMENTO;
    }
}
