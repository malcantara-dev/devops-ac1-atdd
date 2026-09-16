package org.example.grupo_7_praticaatdd.domain.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

// O dominio so garante que a senha existe.
// A criptografia acontece antes, na camada de service, com o BCrypt.
@Embeddable
public class SenhaCriptografada {

    @Column(name = "senha", nullable = false)
    private String valor;

    protected SenhaCriptografada() {
    }

    public SenhaCriptografada(String valor) {
        String normalizado = valor == null ? null : valor.trim();
        if (normalizado == null || normalizado.isBlank()) {
            throw new IllegalArgumentException("Senha e obrigatoria");
        }
        this.valor = normalizado;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SenhaCriptografada that)) return false;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
