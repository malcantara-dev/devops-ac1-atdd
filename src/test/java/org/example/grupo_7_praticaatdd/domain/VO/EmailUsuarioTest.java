package org.example.grupo_7_praticaatdd.domain.VO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailUsuarioTest {

    @Test
    @DisplayName("Deve normalizar o e-mail para minusculo e sem espacos")
    void deveNormalizarOEmail() {
        EmailUsuario email = new EmailUsuario("  Teste@Email.COM  ");

        assertEquals("teste@email.com", email.getValor());
    }

    @Test
    @DisplayName("Nao deve aceitar e-mail nulo ou em branco")
    void naoDeveAceitarEmailVazio() {
        assertEquals("E-mail e obrigatorio",
                assertThrows(IllegalArgumentException.class, () -> new EmailUsuario(null)).getMessage());
        assertThrows(IllegalArgumentException.class, () -> new EmailUsuario("   "));
    }

    @Test
    @DisplayName("Nao deve aceitar e-mail fora do formato")
    void naoDeveAceitarEmailInvalido() {
        assertEquals("E-mail invalido",
                assertThrows(IllegalArgumentException.class, () -> new EmailUsuario("teste-email.com")).getMessage());
        assertThrows(IllegalArgumentException.class, () -> new EmailUsuario("teste@email"));
    }

    @Test
    @DisplayName("Dois e-mails com o mesmo valor devem ser iguais")
    void deveCompararPeloValor() {
        EmailUsuario teste = new EmailUsuario("teste@email.com");
        EmailUsuario mesmoTeste = new EmailUsuario("teste@email.com");
        EmailUsuario outro = new EmailUsuario("joao@email.com");

        assertEquals(teste, teste);
        assertEquals(teste, mesmoTeste);
        assertEquals(teste.hashCode(), mesmoTeste.hashCode());
        assertNotEquals(teste, outro);
        assertTrue(teste.equals(mesmoTeste));
        assertFalse(teste.equals("teste@email.com"));
    }

    @Test
    @DisplayName("O construtor vazio existe para o JPA conseguir montar o objeto")
    void deveTerConstrutorVazioParaOJpa() {
        assertNull(new EmailUsuario().getValor());
    }
}
