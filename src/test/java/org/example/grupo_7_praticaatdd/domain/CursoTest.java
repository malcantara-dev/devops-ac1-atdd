package org.example.grupo_7_praticaatdd.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CursoTest {

    @Test
    @DisplayName("Curso criado com titulo e descricao guarda os dois")
    void deveGuardarTituloEDescricao() {
        Curso curso = new Curso("Java Basico", "Curso introdutorio");

        assertNull(curso.getId());
        assertEquals("Java Basico", curso.getTitulo());
        assertEquals("Curso introdutorio", curso.getDescricao());
    }

    @Test
    @DisplayName("A descricao e opcional")
    void deveAceitarCursoSemDescricao() {
        Curso curso = new Curso("Java Avancado");

        assertEquals("Java Avancado", curso.getTitulo());
        assertNull(curso.getDescricao());
    }

    @Test
    @DisplayName("Curso montado pelo JPA sem descricao nao deve quebrar")
    void naoDeveQuebrarQuandoOJpaNaoPreencherADescricao() {
        Curso curso = new Curso();

        assertNull(curso.getDescricao());
    }
}
