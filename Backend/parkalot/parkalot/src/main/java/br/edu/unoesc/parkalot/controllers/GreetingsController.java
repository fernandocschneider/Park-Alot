package br.edu.unoesc.parkalot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por fornecer uma rota simples de saudação.
 * 
 * @author Jean Toral
 */
@RestController
public class GreetingsController {

    /**
     * Endpoint para exibir a página inicial.
     * 
     * @return uma string com o nome da página inicial ("index").
     */
    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
