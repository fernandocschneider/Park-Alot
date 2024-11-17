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

/**
 * Controlador responsável pela gestão das reservas de vagas de estacionamento.
 * 
 * @author Jean Toral
 */
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

    /**
     * Endpoint para listar todas as reservas registradas.
     * 
     * @return uma lista de reservas com status HTTP 200 OK.
     */
    @GetMapping(value = "listaReservas")
    public ResponseEntity<List<Reserva>> listaReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
    }

    /**
     * Endpoint para salvar uma nova reserva.
     * 
     * @param reserva o objeto reserva a ser salvo.
     * @return a reserva criada com status HTTP 201 (Created).
     */
    @PostMapping(value = "salvarReserva")
    public ResponseEntity<?> salvarReserva(@RequestBody Reserva reserva) {
        reserva = reservaRepository.save(reserva);
        return new ResponseEntity<Reserva>(HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualizar uma reserva existente.
     * 
     * @param reserva o objeto reserva com os novos dados.
     * @return a reserva atualizada ou uma mensagem de erro caso o ID não seja
     *         informado.
     */
    @PutMapping(value = "atualizarReserva")
    public ResponseEntity<?> atualizarReserva(@RequestBody Reserva reserva) {
        if (reserva.getId() == null) {
            return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
        }
        reserva = reservaRepository.saveAndFlush(reserva);
        return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
    }

    /**
     * Endpoint para excluir uma reserva com base no ID.
     * 
     * @param idReserva o ID da reserva a ser excluída.
     * @return resposta com status HTTP 200 OK.
     */
    @GetMapping(value = "deleteReserva")
    public ResponseEntity<Reserva> deleteReserva(@RequestParam Long idReserva) {
        reservaRepository.deleteById(idReserva);
        return new ResponseEntity<Reserva>(HttpStatus.OK);
    }

    /**
     * Endpoint para reservar uma vaga para um veículo de um cliente.
     * O método verifica se o veículo pertence ao cliente, se a vaga está disponível
     * e
     * realiza a reserva, associando o veículo à vaga e marcando a vaga como
     * ocupada.
     * 
     * @param spotId   o ID da vaga a ser reservada.
     * @param plate    a placa do veículo a ser associado à reserva.
     * @param clientId o ID do cliente que está realizando a reserva.
     * @return a reserva criada com status HTTP 201 (Created) ou uma mensagem de
     *         erro.
     */
    @PostMapping(path = "/reservarVaga", produces = "application/json")
    public ResponseEntity<?> reservarVaga(
            @RequestParam Long spotId,
            @RequestParam String plate,
            @RequestParam Long clientId) {

        System.out.println("Spot ID: " + spotId);
        System.out.println("Plate: " + plate);
        System.out.println("Client ID: " + clientId);

        try {
            // Verifica se o cliente existe
            Cliente cliente = clienteRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            // Verifica se o veículo existe
            Veiculo veiculo = veiculoRepository.findByPlate(plate)
                    .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

            // Verifica se o veículo pertence ao cliente
            if (!veiculo.getOwner().getId().equals(clientId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("O veículo não pertence ao cliente especificado.");
            }

            // Verifica se a vaga existe e está disponível
            Vaga vaga = vagaRepository.findById(spotId)
                    .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

            if (!vaga.getAvailable()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Vaga já está ocupada");
            }

            // Marca a vaga como ocupada
            vaga.setAvailable(false);
            vagaRepository.save(vaga);

            // Cria a reserva
            Reserva reserva = new Reserva();
            reserva.setVeiculo(veiculo);
            reserva.setSpot(vaga);
            reserva.setStatus("RESERVADO");
            reserva.setCliente(cliente);

            // Salva a reserva
            Reserva reservaSalva = reservaRepository.save(reserva);

            return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalva);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar reserva: " + e.getMessage());
        }
    }

    /**
     * Endpoint para excluir uma reserva existente.
     * Caso a reserva seja excluída, a vaga associada será marcada como disponível
     * novamente.
     * 
     * @param booking_id o ID da reserva a ser excluída.
     * @return resposta com status HTTP 204 (No Content) ou erro caso a reserva não
     *         seja encontrada.
     */
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

        // Marca a vaga como disponível novamente
        vaga.setAvailable(true);
        vagaRepository.save(vaga);

        // Exclui a reserva
        reservaRepository.deleteById(booking_id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para listar todas as reservas de um cliente específico.
     * 
     * @param userId o ID do cliente cujas reservas serão listadas.
     * @return uma lista de reservas do cliente ou erro caso o cliente não tenha
     *         reservas.
     */
    @GetMapping("/listaReservasCliente")
    public ResponseEntity<List<Reserva>> getReservasByClienteId(@RequestParam Long userId) {
        List<Reserva> reservas = reservaRepository.findByClienteId(userId);
        if (reservas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(reservas);
    }
}
