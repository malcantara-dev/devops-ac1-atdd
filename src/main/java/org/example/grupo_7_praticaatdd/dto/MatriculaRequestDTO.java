package org.example.grupo_7_praticaatdd.dto;

import jakarta.validation.constraints.NotNull;

public class MatriculaRequestDTO {

    @NotNull(message = "usuarioId e obrigatorio")
    private Long usuarioId;

    @NotNull(message = "cursoId e obrigatorio")
    private Long cursoId;

    // Quando true, a matricula consome um credito de curso gratuito.
    private boolean bonus;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }
}
