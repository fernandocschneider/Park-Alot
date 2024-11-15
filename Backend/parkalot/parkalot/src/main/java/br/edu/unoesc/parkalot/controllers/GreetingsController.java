package br.edu.unoesc.parkalot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável por lidar com as saudações e mapeamentos básicos.
 * Exemplo de rota simples que retorna uma string.
 * 
 * @author Jean Toral
 */
@RestController
public class GreetingsController {

    /**
     * Método responsável por lidar com requisições GET para a rota "/home".
     * 
     * @return Retorna a string "index", que pode ser utilizada para renderizar
     *         uma página de boas-vindas ou outro conteúdo.
     */
    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
