package org.example.grupo_7_praticaatdd.dto;

public class MatriculaResponseDTO {

    private final Long id;
    private final Long usuarioId;
    private final String cursoTitulo;
    private final String status;
    private final Double notaFinal;
    private final Double notaParcial;
    private final boolean bonus;

    public MatriculaResponseDTO(Long id, Long usuarioId, String cursoTitulo, String status,
                                Double notaFinal, Double notaParcial, boolean bonus) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.cursoTitulo = cursoTitulo;
        this.status = status;
        this.notaFinal = notaFinal;
        this.notaParcial = notaParcial;
        this.bonus = bonus;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getCursoTitulo() {
        return cursoTitulo;
    }

    public String getStatus() {
        return status;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public Double getNotaParcial() {
        return notaParcial;
    }

    public boolean isBonus() {
        return bonus;
    }
}
