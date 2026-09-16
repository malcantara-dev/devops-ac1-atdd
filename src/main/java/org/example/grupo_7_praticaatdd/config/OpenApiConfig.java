package org.example.grupo_7_praticaatdd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gamificacaoOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API Educacao Continuada Gamificada")
                .version("1.0")
                .description("Grupo 7 - ATDD: creditos de curso gratuito por conclusao com aproveitamento"));
    }
}
