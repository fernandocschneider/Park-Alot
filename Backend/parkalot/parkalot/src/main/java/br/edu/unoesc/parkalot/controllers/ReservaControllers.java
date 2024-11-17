package br.edu.unoesc.parkalot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.parkalot.model.Cliente;
import br.edu.unoesc.parkalot.model.Reserva;
import br.edu.unoesc.parkalot.model.Vaga;
import br.edu.unoesc.parkalot.model.Veiculo;
import br.edu.unoesc.parkalot.repository.clienteRepository;

@RestController
public class ReservaControllers {

    @Autowired
    private br.edu.unoesc.parkalot.repository.reservaRepository reservaRepository;

    @Autowired
    private br.edu.unoesc.parkalot.repository.vagaRepository vagaRepository;

    @Autowired
    private br.edu.unoesc.parkalot.repository.veiculoRepository veiculoRepository;

    @Autowired
    private clienteRepository clienteRepository;

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
            @RequestParam String plate,
            @RequestParam Long clientId) {

        System.out.println("Spot ID: " + spotId);
        System.out.println("Plate: " + plate);
        System.out.println("Client ID: " + clientId);

        try {
            Cliente cliente = clienteRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            Veiculo veiculo = veiculoRepository.findByPlate(plate)
                    .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

            if (!veiculo.getOwner().getId().equals(clientId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("O veículo não pertence ao cliente especificado.");
            }

            Vaga vaga = vagaRepository.findById(spotId)
                    .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

            if (!vaga.getAvailable()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Vaga já está ocupada");
            }

            vaga.setAvailable(false);
            vagaRepository.save(vaga);

            Reserva reserva = new Reserva();
            reserva.setVeiculo(veiculo);
            reserva.setSpot(vaga);
            reserva.setStatus("RESERVADO");
            reserva.setCliente(cliente);

            Reserva reservaSalva = reservaRepository.save(reserva);

            return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalva);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar reserva: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluirReserva")
    public ResponseEntity<Void> excluirReserva(@RequestParam Long booking_id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(booking_id);
        if (!reservaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Reserva reserva = reservaOptional.get();
        Vaga vaga = reserva.getSpot();

        if (vaga == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        vaga.setAvailable(true);
        vagaRepository.save(vaga);

        reservaRepository.deleteById(booking_id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listaReservasCliente")
    public ResponseEntity<List<Reserva>> getReservasByClienteId(@RequestParam Long userId) {
        List<Reserva> reservas = reservaRepository.findByClienteId(userId);
        if (reservas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(reservas);
    }
}
