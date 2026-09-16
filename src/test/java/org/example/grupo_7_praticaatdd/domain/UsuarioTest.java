package org.example.grupo_7_praticaatdd.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioTest {

    @Test
    @DisplayName("O usuario nasce com os dados informados e uma assinatura basica vazia")
    void deveNascerComAssinaturaBasica() {
        Usuario usuario = new Usuario("Joao", "JOAO@Email.com", "hash-da-senha");

        assertNull(usuario.getId());
        assertEquals("Joao", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals("hash-da-senha", usuario.getSenha());
        assertEquals(PlanoAssinatura.BASICO, usuario.getAssinatura().getPlano());
        assertTrue(usuario.getMatriculas().isEmpty());
    }

    @Test
    @DisplayName("Matricular em um curso cria uma matricula comum, nao bonus")
    void deveMatricularEmCursoComum() {
        Usuario usuario = new Usuario("Joao", "joao@email.com", "hash-da-senha");
        Curso curso = new Curso("Java Basico", "Curso introdutorio");

        Matricula matricula = usuario.matricularEm(curso);

        assertEquals(1, usuario.getMatriculas().size());
        assertEquals(usuario, matricula.getUsuario());
        assertEquals(curso, matricula.getCurso());
        assertTrue(matricula.estaEmAndamento());
    }

    @Test
    @DisplayName("Desbloquear um curso cria uma matricula marcada como bonus")
    void deveCriarMatriculaBonusAoDesbloquear() {
        Usuario usuario = new Usuario("Joao", "joao@email.com", "hash-da-senha");
        usuario.getAssinatura().adicionarCreditos(1);

        usuario.desbloquearCurso(new Curso("Java Avancado"));

        assertTrue(usuario.getMatriculas().get(0).isBonus());
    }

    @Test
    @DisplayName("Matriculas adicionadas manualmente entram na lista do usuario")
    void deveAdicionarMatriculaNaLista() {
        Usuario usuario = new Usuario("Joao", "joao@email.com", "hash-da-senha");
        Matricula matricula = new Matricula(usuario, new Curso("Java Basico"), false);

        usuario.adicionarMatricula(matricula);

        assertEquals(1, usuario.getMatriculas().size());
        assertEquals(matricula, usuario.getMatriculas().get(0));
    }
}
