package org.example.grupo_7_praticaatdd.domain.VO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NomeUsuarioTest {

    @Test
    @DisplayName("Deve guardar o nome sem espacos nas pontas")
    void deveNormalizarONome() {
        NomeUsuario nome = new NomeUsuario("  Teste  ");

        assertEquals("Teste", nome.getValor());
    }

    @Test
    @DisplayName("Nao deve aceitar nome nulo, vazio ou so com espacos")
    void naoDeveAceitarNomeInvalido() {
        assertEquals("Nome e obrigatorio",
                assertThrows(IllegalArgumentException.class, () -> new NomeUsuario(null)).getMessage());
        assertThrows(IllegalArgumentException.class, () -> new NomeUsuario(""));
        assertThrows(IllegalArgumentException.class, () -> new NomeUsuario("   "));
    }

    @Test
    @DisplayName("Dois nomes com o mesmo valor devem ser iguais")
    void deveCompararPeloValor() {
        NomeUsuario teste = new NomeUsuario("Teste");
        NomeUsuario mesmoTeste = new NomeUsuario("Teste");
        NomeUsuario outro = new NomeUsuario("Joao");

        assertEquals(teste, teste);
        assertEquals(teste, mesmoTeste);
        assertEquals(teste.hashCode(), mesmoTeste.hashCode());
        assertNotEquals(teste, outro);
        assertTrue(teste.equals(mesmoTeste));
        assertFalse(teste.equals("Teste"));
    }

    @Test
    @DisplayName("O construtor vazio existe para o JPA conseguir montar o objeto")
    void deveTerConstrutorVazioParaOJpa() {
        assertNull(new NomeUsuario().getValor());
    }
}
