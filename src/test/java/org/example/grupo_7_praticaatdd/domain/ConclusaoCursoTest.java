package org.example.grupo_7_praticaatdd.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConclusaoCursoTest {

    @Test
    @DisplayName("Cenario 1 - curso concluido com nota maior ou igual a 7 gera 3 creditos")
    void deveAdicionarTresCreditosQuandoConcluirCursoComNotaAlta() {
        // GIVEN - dado um usuario com um curso em andamento
        Usuario usuario = new Usuario("Ana");
        Curso curso = new Curso("Spring Boot Fundamentos");
        Matricula matricula = new Matricula(usuario, curso, false);

        // WHEN - quando este curso e finalizado com nota 8
        usuario.concluirCurso(matricula, 8.0);

        // THEN - entao o curso fica concluido e 3 creditos sao acrescentados
        assertEquals(StatusMatricula.CONCLUIDO, matricula.getStatus());
        assertEquals(3, usuario.getAssinatura().getCreditosCursos());
    }

    @Test
    @DisplayName("Cenario 2 - curso concluido com nota menor que 7 nao gera credito")
    void naoDeveAdicionarCreditoQuandoConcluirCursoComNotaBaixa() {
        // GIVEN - dado um usuario com um curso em andamento
        Usuario usuario = new Usuario("Bruno");
        Curso curso = new Curso("Spring Boot Fundamentos");
        Matricula matricula = new Matricula(usuario, curso, false);

        // WHEN - quando este curso e finalizado com nota 5
        usuario.concluirCurso(matricula, 5.0);

        // THEN - entao o curso fica concluido mas nenhum credito e adicionado
        assertEquals(StatusMatricula.CONCLUIDO, matricula.getStatus());
        assertEquals(0, usuario.getAssinatura().getCreditosCursos());
    }
}