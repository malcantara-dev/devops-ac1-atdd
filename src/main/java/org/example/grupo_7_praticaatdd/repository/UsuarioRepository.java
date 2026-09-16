package org.example.grupo_7_praticaatdd.repository;

import org.example.grupo_7_praticaatdd.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
