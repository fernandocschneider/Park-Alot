package br.edu.unoesc.parkalot.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.parkalot.model.Reserva;
import br.edu.unoesc.parkalot.model.Vaga;
import br.edu.unoesc.parkalot.model.Veiculo;
import br.edu.unoesc.parkalot.repository.reservaRepository;
import br.edu.unoesc.parkalot.repository.vagaRepository;
import br.edu.unoesc.parkalot.repository.veiculoRepository;

@RestController
public class RelacionarCarroeVaga {
    @Autowired
    private veiculoRepository veiculoRepository;

    @Autowired
    private vagaRepository vagaRepository;

    @Autowired
    private reservaRepository reservaRepository;

    @PostMapping(value = "relacionarCarroVaga")
    public ResponseEntity<?> relacionarCarroVaga(@RequestBody Map<String, Long> dados) {
        Long carroId = dados.get("carroId");
        Long vagaId = dados.get("vagaId");

        Veiculo veiculo = veiculoRepository.findById(carroId).orElse(null);
        Vaga vaga = vagaRepository.findById(vagaId).orElse(null);

        if (veiculo == null || vaga == null) {
            return new ResponseEntity<String>("Carro ou Vaga não encontrados", HttpStatus.NOT_FOUND);
        }

        if (!vaga.getAvailable()) {
            return new ResponseEntity<String>("Vaga já ocupada", HttpStatus.CONFLICT);
        }

        Reserva reserva = new Reserva();
        reserva.setVeiculo(veiculo);
        reserva.setSpot(vaga);
        reserva.setStatus("Reservada");

        vaga.setAvailable(false);
        vagaRepository.save(vaga);

        reservaRepository.save(reserva);

        return new ResponseEntity<Reserva>(reserva, HttpStatus.CREATED);
    }
}
