package org.example.grupo_7_praticaatdd.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CursoEmAndamentoTest {

    @Test
    @DisplayName("Cenario 3 - um curso em andamento e nenhum finalizado mantem 0 creditos")
    void naoDeveTerCreditoComApenasUmCursoEmAndamento() {
        // GIVEN - dado um usuario novo e um curso
        Usuario usuario = new Usuario("Carla");
        Curso curso = new Curso("Java Basico");

        // WHEN - quando ele se matricula e nao finaliza nada
        usuario.matricularEm(curso);

        // THEN - entao existe 1 matricula em andamento e 0 creditos
        assertEquals(1, usuario.getMatriculas().size());
        assertEquals(StatusMatricula.EM_ANDAMENTO, usuario.getMatriculas().get(0).getStatus());
        assertEquals(0, usuario.getAssinatura().getCreditosCursos());
    }

    @Test
    @DisplayName("Cenario 4 - media parcial boa sem finalizar o curso nao gera credito")
    void naoDeveAdicionarCreditoComNotaParcialBoaSemFinalizar() {
        // GIVEN - dado um usuario com um curso em andamento
        Usuario usuario = new Usuario("Diego");
        Curso curso = new Curso("Java Basico");
        Matricula matricula = new Matricula(usuario, curso, false);

        // WHEN - quando a media parcial 8 e registrada, sem finalizar o curso
        matricula.registrarNotaParcial(8.0);

        // THEN - entao a nota fica guardada, o curso segue em andamento e nao ha credito
        assertEquals(8.0, matricula.getNotaParcial());
        assertEquals(StatusMatricula.EM_ANDAMENTO, matricula.getStatus());
        assertEquals(0, usuario.getAssinatura().getCreditosCursos());
    }
}