package org.example.grupo_7_praticaatdd.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DesbloqueioCursoTest {

    @Test
    @DisplayName("Cenario 5 - com 1 credito o curso e liberado e o credito e consumido")
    void deveLiberarCursoEConsumirCreditoQuandoTemCredito() {
        // GIVEN - dado um usuario com 1 credito e um curso nao adquirido
        Usuario usuario = new Usuario("Elisa");
        usuario.getAssinatura().adicionarCreditos(1);
        Curso curso = new Curso("Java Avancado");

        // WHEN - quando ele solicita o desbloqueio deste curso
        usuario.desbloquearCurso(curso);

        // THEN - entao o curso entra na biblioteca dele e o credito e consumido
        assertEquals(1, usuario.getMatriculas().size());
        assertEquals(curso, usuario.getMatriculas().get(0).getCurso());
        assertEquals(0, usuario.getAssinatura().getCreditosCursos());
    }

    @Test
    @DisplayName("Cenario 6 - sem credito o curso nao e liberado e avisa creditos insuficientes")
    void naoDeveLiberarCursoQuandoNaoTemCredito() {
        // GIVEN - dado um usuario sem nenhum credito e um curso nao adquirido
        Usuario usuario = new Usuario("Fabio");
        Curso curso = new Curso("Java Avancado");

        // WHEN - quando ele solicita o desbloqueio deste curso
        IllegalStateException erro = assertThrows(
                IllegalStateException.class,
                () -> usuario.desbloquearCurso(curso)
        );

        // THEN - entao o curso nao e liberado e o aviso e exibido
        assertEquals("Creditos insuficientes", erro.getMessage());
        assertTrue(usuario.getMatriculas().isEmpty());
    }
}