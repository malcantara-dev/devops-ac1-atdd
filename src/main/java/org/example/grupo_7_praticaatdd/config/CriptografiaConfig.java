package org.example.grupo_7_praticaatdd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Camada CONFIG: registra beans que o Spring vai injetar nas outras camadas.
@Configuration
public class CriptografiaConfig {

    // O bean nasce aqui e e injetado no UsuarioService.
    // Esse e o exemplo pratico de Injecao de Dependencia do projeto.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
