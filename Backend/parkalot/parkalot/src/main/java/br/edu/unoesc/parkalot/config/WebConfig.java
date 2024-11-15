package br.edu.unoesc.parkalot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração do CORS (Cross-Origin Resource Sharing) para a aplicação Spring
 * Boot.
 * Esta classe configura as permissões de CORS, permitindo que as requisições
 * de diferentes origens sejam aceitas pela aplicação.
 * 
 * A classe implementa a interface {@link WebMvcConfigurer}, permitindo
 * customizar
 * as configurações padrão do Spring MVC.
 * 
 * @author Jean Toral
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adiciona configurações de CORS para a aplicação.
     * Permite que o frontend, hospedado no endereço {@code http://localhost:8080},
     * faça requisições ao backend, utilizando os métodos HTTP especificados.
     * 
     * @param registry Objeto usado para registrar as configurações de CORS.
     */
    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas as rotas da API.
                .allowedOrigins("http://localhost:8080") // Permite origens de localhost na porta 8080.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite os métodos GET, POST, PUT e DELETE.
                .allowedHeaders("*"); // Permite todos os cabeçalhos de requisição.
    }
}
