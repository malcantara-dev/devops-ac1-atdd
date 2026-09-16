package org.example.grupo_7_praticaatdd.repository;

import org.example.grupo_7_praticaatdd.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
