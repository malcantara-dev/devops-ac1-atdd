package org.example.grupo_7_praticaatdd.domain.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

// A descricao foi modelada como opcional, entao aceita null.
@Embeddable
public class DescricaoCurso {

    @Column(name = "descricao", length = 1000)
    private String valor;

    protected DescricaoCurso() {
    }

    public DescricaoCurso(String valor) {
        this.valor = valor == null ? null : valor.trim();
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DescricaoCurso that)) return false;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
