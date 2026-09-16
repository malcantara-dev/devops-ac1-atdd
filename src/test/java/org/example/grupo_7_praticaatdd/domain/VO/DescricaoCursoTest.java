package org.example.grupo_7_praticaatdd.domain.VO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DescricaoCursoTest {

    @Test
    @DisplayName("Deve guardar a descricao sem espacos nas pontas")
    void deveNormalizarADescricao() {
        DescricaoCurso descricao = new DescricaoCurso("  Curso introdutorio  ");

        assertEquals("Curso introdutorio", descricao.getValor());
    }

    @Test
    @DisplayName("Deve aceitar descricao nula porque ela e opcional")
    void deveAceitarDescricaoNula() {
        assertNull(new DescricaoCurso(null).getValor());
    }

    @Test
    @DisplayName("Duas descricoes com o mesmo valor devem ser iguais")
    void deveCompararPeloValor() {
        DescricaoCurso uma = new DescricaoCurso("Curso introdutorio");
        DescricaoCurso mesma = new DescricaoCurso("Curso introdutorio");
        DescricaoCurso outra = new DescricaoCurso("Curso avancado");

        assertEquals(uma, uma);
        assertEquals(uma, mesma);
        assertEquals(uma.hashCode(), mesma.hashCode());
        assertNotEquals(uma, outra);
        assertTrue(uma.equals(mesma));
        assertFalse(uma.equals("Curso introdutorio"));
    }

    @Test
    @DisplayName("O construtor vazio existe para o JPA conseguir montar o objeto")
    void deveTerConstrutorVazioParaOJpa() {
        assertNull(new DescricaoCurso().getValor());
    }
}
