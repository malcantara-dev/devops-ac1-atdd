package org.example.grupo_7_praticaatdd.domain.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.regex.Pattern;

// Value Object que concentra as regras de formato do e-mail.
@Embeddable
public class EmailUsuario {

    private static final Pattern PADRAO_EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Column(name = "email", nullable = false, unique = true)
    private String valor;

    protected EmailUsuario() {
    }

    public EmailUsuario(String valor) {
        String normalizado = valor == null ? null : valor.trim().toLowerCase();
        if (normalizado == null || normalizado.isBlank()) {
            throw new IllegalArgumentException("E-mail e obrigatorio");
        }
        if (!PADRAO_EMAIL.matcher(normalizado).matches()) {
            throw new IllegalArgumentException("E-mail invalido");
        }
        this.valor = normalizado;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailUsuario that)) return false;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
