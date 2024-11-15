package br.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Permite apenas o localhost:8080
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*") // Permite todos os headers
                .allowCredentials(true) // Permite envio de cookies e credenciais
                .maxAge(3600); // Define o cache das requisições preflight por 1 hora
    }
}
