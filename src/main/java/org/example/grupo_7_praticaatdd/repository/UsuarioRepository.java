package org.example.grupo_7_praticaatdd.repository;

import org.example.grupo_7_praticaatdd.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Camada REPOSITORY: o Spring Data gera a implementacao sozinho.
// De JpaRepository ja herdamos findAll, findById, save e deleteById.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Como EmailUsuario e um Value Object embutido, o Spring Data navega ate email.valor.
    boolean existsByEmailValor(String email);
}
