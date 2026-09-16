package org.example.grupo_7_praticaatdd.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AssinaturaTest {

    @Test
    @DisplayName("Toda assinatura nasce no plano basico, zerada")
    void deveNascerBasicaEZerada() {
        Assinatura assinatura = new Assinatura();

        assertNull(assinatura.getId());
        assertEquals(PlanoAssinatura.BASICO, assinatura.getPlano());
        assertEquals(0, assinatura.getCreditosCursos());
        assertEquals(0, assinatura.getCursosConcluidosComSucesso());
    }

    @Test
    @DisplayName("Cada conclusao com sucesso soma 1 conclusao e 3 creditos")
    void deveContarConclusoesECreditos() {
        Assinatura assinatura = new Assinatura();

        assinatura.registrarConclusaoComSucesso();
        assinatura.registrarConclusaoComSucesso();

        assertEquals(2, assinatura.getCursosConcluidosComSucesso());
        assertEquals(2 * Assinatura.CREDITOS_POR_CONCLUSAO, assinatura.getCreditosCursos());
    }

    @Test
    @DisplayName("Consumir credito desconta um do saldo")
    void deveDescontarUmCreditoAoConsumir() {
        Assinatura assinatura = new Assinatura();
        assinatura.adicionarCreditos(2);

        assinatura.consumirCredito();

        assertEquals(1, assinatura.getCreditosCursos());
    }
}
