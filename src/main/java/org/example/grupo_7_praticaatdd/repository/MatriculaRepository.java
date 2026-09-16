package org.example.grupo_7_praticaatdd.repository;

import org.example.grupo_7_praticaatdd.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Metodo derivado pelo nome: o Spring Data monta a consulta a partir dele.
    List<Matricula> findByUsuarioId(Long usuarioId);
}
