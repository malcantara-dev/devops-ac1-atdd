package org.example.grupo_7_praticaatdd.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class ConcluirMatriculaRequestDTO {

    @NotNull(message = "notaFinal e obrigatoria")
    @DecimalMin(value = "0.0", message = "Nota minima 0")
    @DecimalMax(value = "10.0", message = "Nota maxima 10")
    private Double notaFinal;

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }
}
