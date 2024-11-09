package br.edu.unoesc.parkalot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping(value = "criarVaga")
    public ResponseEntity<Vaga> criarVaga(@RequestBody Vaga vaga) {
        vaga = vagaRepository.save(vaga);
        return new ResponseEntity<Vaga>(vaga, HttpStatus.CREATED);
    }

    @GetMapping(value = "atualizarVaga")
    public ResponseEntity<?> atualizarVaga(@RequestBody Vaga vaga) {
        if (vaga.getId() == null) {
            return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
        }
        vaga = vagaRepository.saveAndFlush(vaga);
        return new ResponseEntity<Vaga>(vaga, HttpStatus.OK);
    }
}
