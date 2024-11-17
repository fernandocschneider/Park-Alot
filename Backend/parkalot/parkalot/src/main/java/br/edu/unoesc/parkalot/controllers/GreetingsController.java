package br.edu.unoesc.parkalot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
