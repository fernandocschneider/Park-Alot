package br.edu.unoesc.parkalot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.parkalot.model.Reserva;
import br.edu.unoesc.parkalot.model.Vaga;
import br.edu.unoesc.parkalot.model.Veiculo;

@RestController
public class ReservaControllers {

    @Autowired
    private br.edu.unoesc.parkalot.repository.reservaRepository reservaRepository;

    @Autowired
    private br.edu.unoesc.parkalot.repository.vagaRepository vagaRepository;

    @Autowired
    private br.edu.unoesc.parkalot.repository.veiculoRepository veiculoRepository;

    @GetMapping(value = "listaReservas")
    public ResponseEntity<List<Reserva>> listaReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
    }

    @PostMapping(value = "salvarReserva")
    public ResponseEntity<?> salvarReserva(@RequestBody Reserva reserva) {
        reserva = reservaRepository.save(reserva);
        return new ResponseEntity<Reserva>(HttpStatus.CREATED);
    }

    @PutMapping(value = "atualizarReserva")
    public ResponseEntity<?> atualizarReserva(@RequestBody Reserva reserva) {
        if (reserva.getId() == null) {
            return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
        }
        reserva = reservaRepository.saveAndFlush(reserva);
        return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
    }

    @GetMapping(value = "deleteReserva")
    public ResponseEntity<Reserva> deleteReserva(@RequestParam Long idReserva) {
        reservaRepository.deleteById(idReserva);
        return new ResponseEntity<Reserva>(HttpStatus.OK);
    }

    @PostMapping(path = "/reservarVaga", produces = "application/json")
    public ResponseEntity<?> reservarVaga(
            @RequestParam Long spotId,
            @RequestParam String plate) {

        Veiculo veiculo = veiculoRepository.findByPlate(plate)
                .orElse(null);

        if (veiculo == null) {
            return new ResponseEntity<String>("Veículo não encontrado", HttpStatus.NOT_FOUND);
        }

        Vaga vaga = vagaRepository.findById(spotId)
                .orElse(null);

        if (vaga == null) {
            return new ResponseEntity<String>("Vaga não encontrada", HttpStatus.NOT_FOUND);
        }

        if (!vaga.getAvailable()) {
            return new ResponseEntity<String>("Vaga indisponível", HttpStatus.CONFLICT);
        }

        boolean vagaOcupada = reservaRepository.verificarReserva(spotId);

        if (vagaOcupada) {
            return new ResponseEntity<String>("Vaga já ocupada", HttpStatus.CONFLICT);
        }

        Reserva reserva = new Reserva();
        reserva.setVeiculo(veiculo);
        reserva.setSpot(vaga);
        reserva.setStatus("Reservada");

        vaga.setAvailable(false);
        vagaRepository.save(vaga);

        Reserva reservaSalva = reservaRepository.save(reserva);

        return new ResponseEntity<Reserva>(reservaSalva, HttpStatus.CREATED);
    }
}
