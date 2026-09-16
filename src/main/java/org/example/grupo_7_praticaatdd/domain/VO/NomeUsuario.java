package org.example.grupo_7_praticaatdd.domain.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

// Value Object: nao tem identidade propria, representa apenas um valor validado.
@Embeddable
public class NomeUsuario {

    @Column(name = "nome", nullable = false)
    private String valor;

    protected NomeUsuario() {
    }

    public NomeUsuario(String valor) {
        String normalizado = valor == null ? null : valor.trim();
        if (normalizado == null || normalizado.isBlank()) {
            throw new IllegalArgumentException("Nome e obrigatorio");
        }
        this.valor = normalizado;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomeUsuario that)) return false;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
