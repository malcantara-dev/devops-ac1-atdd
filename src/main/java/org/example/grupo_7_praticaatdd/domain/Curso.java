package org.example.grupo_7_praticaatdd.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.example.grupo_7_praticaatdd.domain.VO.DescricaoCurso;
import org.example.grupo_7_praticaatdd.domain.VO.TituloCurso;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Embedded guarda o Value Object dentro da propria tabela de cursos.
    @Embedded
    private TituloCurso titulo;

    @Embedded
    private DescricaoCurso descricao;

    protected Curso() {
    }

    public Curso(String titulo) {
        this(titulo, null);
    }

    public Curso(String titulo, String descricao) {
        this.titulo = new TituloCurso(titulo);
        this.descricao = new DescricaoCurso(descricao);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo.getValor();
    }

    public String getDescricao() {
        return descricao == null ? null : descricao.getValor();
    }
}
