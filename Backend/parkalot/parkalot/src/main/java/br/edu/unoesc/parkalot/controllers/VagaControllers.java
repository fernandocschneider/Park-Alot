package br.edu.unoesc.parkalot.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.parkalot.model.Vaga;

@RestController
public class VagaControllers {

    @Autowired
    private br.edu.unoesc.parkalot.repository.vagaRepository vagaRepository;

    @GetMapping(value = "listaVagas")
    public ResponseEntity<List<Vaga>> listaVagas() {
        List<Vaga> vagas = vagaRepository.findAll();
        return new ResponseEntity<List<Vaga>>(vagas, HttpStatus.OK);
    }

    @GetMapping(value = "verificarReserva")
    public ResponseEntity<Boolean> verificarReserva(
            @RequestParam Long idVaga,
            @RequestParam Date initialDate,
            @RequestParam Date finalDate) {
        boolean isReserved = vagaRepository.verificarReserva(idVaga, initialDate, finalDate);
        return new ResponseEntity<>(isReserved, HttpStatus.OK);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Vaga>> listarVagasDisponiveis() {
        List<Vaga> vagasDisponiveis = vagaRepository.findByAvailable(true);

        if (vagasDisponiveis.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(vagasDisponiveis);
    }
}
