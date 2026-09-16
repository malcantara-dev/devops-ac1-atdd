package org.example.grupo_7_praticaatdd.domain.VO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SenhaCriptografadaTest {

    @Test
    @DisplayName("Deve guardar a senha ja criptografada")
    void deveGuardarASenha() {
        SenhaCriptografada senha = new SenhaCriptografada("  $2a$10$hashDeExemplo  ");

        assertEquals("$2a$10$hashDeExemplo", senha.getValor());
    }

    @Test
    @DisplayName("Nao deve aceitar senha nula ou em branco")
    void naoDeveAceitarSenhaInvalida() {
        assertEquals("Senha e obrigatoria",
                assertThrows(IllegalArgumentException.class, () -> new SenhaCriptografada(null)).getMessage());
        assertThrows(IllegalArgumentException.class, () -> new SenhaCriptografada("   "));
    }

    @Test
    @DisplayName("Duas senhas com o mesmo valor devem ser iguais")
    void deveCompararPeloValor() {
        SenhaCriptografada uma = new SenhaCriptografada("hash-1");
        SenhaCriptografada mesma = new SenhaCriptografada("hash-1");
        SenhaCriptografada outra = new SenhaCriptografada("hash-2");

        assertEquals(uma, uma);
        assertEquals(uma, mesma);
        assertEquals(uma.hashCode(), mesma.hashCode());
        assertNotEquals(uma, outra);
        assertTrue(uma.equals(mesma));
        assertFalse(uma.equals("hash-1"));
    }

    @Test
    @DisplayName("O construtor vazio existe para o JPA conseguir montar o objeto")
    void deveTerConstrutorVazioParaOJpa() {
        assertNull(new SenhaCriptografada().getValor());
    }
}
