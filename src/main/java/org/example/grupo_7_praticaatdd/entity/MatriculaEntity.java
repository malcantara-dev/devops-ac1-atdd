package org.example.grupo_7_praticaatdd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.example.grupo_7_praticaatdd.domain.StatusMatricula;

@Entity
@Table(name = "matriculas")
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMatricula status;

    @Column(nullable = false)
    private boolean bonus;

    private Double notaFinal;

    private Double notaParcial;

    protected MatriculaEntity() {
    }

    public MatriculaEntity(UsuarioEntity usuario, CursoEntity curso, boolean bonus) {
        this.usuario = usuario;
        this.curso = curso;
        this.bonus = bonus;
        this.status = StatusMatricula.EM_ANDAMENTO;
    }

    public Long getId() {
        return id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public CursoEntity getCurso() {
        return curso;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public boolean isBonus() {
        return bonus;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public Double getNotaParcial() {
        return notaParcial;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public void setNotaParcial(Double notaParcial) {
        this.notaParcial = notaParcial;
    }
}
