package org.example.grupo_7_praticaatdd.domain.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class TituloCurso {

    @Column(name = "titulo", nullable = false)
    private String valor;

    protected TituloCurso() {
    }

    public TituloCurso(String valor) {
        String normalizado = valor == null ? null : valor.trim();
        if (normalizado == null || normalizado.isBlank()) {
            throw new IllegalArgumentException("Titulo e obrigatorio");
        }
        this.valor = normalizado;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TituloCurso that)) return false;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
