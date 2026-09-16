package org.example.grupo_7_praticaatdd.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class NotaRequestDTO {

    @NotNull(message = "nota e obrigatoria")
    @DecimalMin(value = "0.0", message = "nota minima 0")
    @DecimalMax(value = "10.0", message = "nota maxima 10")
    private Double nota;

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
