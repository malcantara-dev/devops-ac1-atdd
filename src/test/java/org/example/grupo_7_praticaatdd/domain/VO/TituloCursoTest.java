package org.example.grupo_7_praticaatdd.domain.VO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TituloCursoTest {

    @Test
    @DisplayName("Deve guardar o titulo sem espacos nas pontas")
    void deveNormalizarOTitulo() {
        TituloCurso titulo = new TituloCurso("  Java Basico  ");

        assertEquals("Java Basico", titulo.getValor());
    }

    @Test
    @DisplayName("Nao deve aceitar titulo nulo ou em branco")
    void naoDeveAceitarTituloInvalido() {
        assertEquals("Titulo e obrigatorio",
                assertThrows(IllegalArgumentException.class, () -> new TituloCurso(null)).getMessage());
        assertThrows(IllegalArgumentException.class, () -> new TituloCurso("   "));
    }

    @Test
    @DisplayName("Dois titulos com o mesmo valor devem ser iguais")
    void deveCompararPeloValor() {
        TituloCurso java = new TituloCurso("Java Basico");
        TituloCurso mesmoJava = new TituloCurso("Java Basico");
        TituloCurso spring = new TituloCurso("Spring Boot");

        assertEquals(java, java);
        assertEquals(java, mesmoJava);
        assertEquals(java.hashCode(), mesmoJava.hashCode());
        assertNotEquals(java, spring);
        assertTrue(java.equals(mesmoJava));
        assertFalse(java.equals("Java Basico"));
    }

    @Test
    @DisplayName("O construtor vazio existe para o JPA conseguir montar o objeto")
    void deveTerConstrutorVazioParaOJpa() {
        assertNull(new TituloCurso().getValor());
    }
}
