package org.example.grupo_7_praticaatdd.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatriculaTest {

    private Usuario novoUsuario() {
        return new Usuario("Joao", "joao@email.com", "senha123");
    }

    @Test
    @DisplayName("A matricula guarda usuario, curso e nasce em andamento")
    void deveNascerEmAndamento() {
        Usuario usuario = novoUsuario();
        Curso curso = new Curso("Java Basico");

        Matricula matricula = new Matricula(usuario, curso, false);

        assertNull(matricula.getId());
        assertEquals(usuario, matricula.getUsuario());
        assertEquals(curso, matricula.getCurso());
        assertFalse(matricula.isBonus());
        assertEquals(StatusMatricula.EM_ANDAMENTO, matricula.getStatus());
        assertEquals(0.0, matricula.getNotaFinal());
        assertEquals(0.0, matricula.getNotaParcial());
        assertTrue(matricula.estaEmAndamento());
    }

    @Test
    @DisplayName("Uma matricula bonus fica marcada como bonus")
    void deveMarcarMatriculaBonus() {
        Matricula matricula = new Matricula(novoUsuario(), new Curso("Java Avancado"), true);

        assertTrue(matricula.isBonus());
    }

    @Test
    @DisplayName("Depois de concluida a matricula sai de andamento e guarda a nota")
    void deveSairDeAndamentoAoConcluir() {
        Matricula matricula = new Matricula(novoUsuario(), new Curso("Java Basico"), false);

        matricula.concluir(9.5);

        assertEquals(StatusMatricula.CONCLUIDO, matricula.getStatus());
        assertEquals(9.5, matricula.getNotaFinal());
        assertFalse(matricula.estaEmAndamento());
        assertTrue(matricula.concluidoComAproveitamento());
    }

    @Test
    @DisplayName("Curso em andamento nao conta como aproveitamento, mesmo com nota parcial alta")
    void naoDeveTerAproveitamentoSemConcluir() {
        Matricula matricula = new Matricula(novoUsuario(), new Curso("Java Basico"), false);

        matricula.registrarNotaParcial(10.0);

        assertEquals(10.0, matricula.getNotaParcial());
        assertFalse(matricula.concluidoComAproveitamento());
    }

    @Test
    @DisplayName("Concluir com nota abaixo de 7 nao gera aproveitamento")
    void naoDeveTerAproveitamentoComNotaBaixa() {
        Matricula matricula = new Matricula(novoUsuario(), new Curso("Java Basico"), false);

        matricula.concluir(Matricula.NOTA_MINIMA_APROVACAO - 0.5);

        assertFalse(matricula.concluidoComAproveitamento());
    }
}
