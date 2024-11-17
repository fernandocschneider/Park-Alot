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
 * Controller responsável pelo gerenciamento das reservas de vagas de
 * estacionamento.
 * Implementa operações CRUD e reserva de vagas.
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
     * Retorna a lista de todas as reservas cadastradas.
     * 
     * @return ResponseEntity contendo a lista de reservas e o status HTTP OK.
     */
    @GetMapping(value = "listaReservas")
    public ResponseEntity<List<Reserva>> listaReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
    }

    /**
     * Salva uma nova reserva no repositório.
     * 
     * @param reserva Objeto Reserva a ser salvo.
     * @return ResponseEntity contendo a reserva criada e o status HTTP CREATED.
     */
    @PostMapping(value = "salvarReserva")
    public ResponseEntity<?> salvarReserva(@RequestBody Reserva reserva) {
        reserva = reservaRepository.save(reserva);
        return new ResponseEntity<Reserva>(HttpStatus.CREATED);
    }

    /**
     * Atualiza uma reserva existente.
     * 
     * @param reserva Objeto Reserva com os dados atualizados.
     * @return ResponseEntity contendo a reserva atualizada ou uma mensagem de erro
     *         se o ID não for informado.
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
     * Exclui uma reserva com base no ID informado.
     * 
     * @param idReserva ID da reserva a ser excluída.
     * @return ResponseEntity com o status HTTP OK.
     */
    @GetMapping(value = "deleteReserva")
    public ResponseEntity<Reserva> deleteReserva(@RequestParam Long idReserva) {
        reservaRepository.deleteById(idReserva);
        return new ResponseEntity<Reserva>(HttpStatus.OK);
    }

    /**
     * Reserva uma vaga de estacionamento para um veículo, verificando a
     * disponibilidade.
     * 
     * @param spotId ID da vaga a ser reservada.
     * @param plate  Placa do veículo que deseja a reserva.
     * @return ResponseEntity contendo a reserva criada ou mensagens de erro com os
     *         respectivos
     *         status HTTP, caso o veículo ou a vaga não sejam encontrados ou
     *         estejam ocupados.
     */
    @PostMapping(path = "/reservarVaga", produces = "application/json")
    public ResponseEntity<?> reservarVaga(
            @RequestParam Long spotId,
            @RequestParam String plate,
            @RequestParam Long clientId) {

        // Log para depuração
        System.out.println("Spot ID: " + spotId);
        System.out.println("Plate: " + plate);
        System.out.println("Client ID: " + clientId);

        try {
            // 1. Verificar se o cliente existe
            Cliente cliente = clienteRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            // 2. Buscar o veículo pelo número da placa
            Veiculo veiculo = veiculoRepository.findByPlate(plate)
                    .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

            // 3. Verificar se o veículo pertence ao cliente
            if (!veiculo.getOwner().getId().equals(clientId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("O veículo não pertence ao cliente especificado.");
            }

            // 4. Buscar a vaga pelo ID
            Vaga vaga = vagaRepository.findById(spotId)
                    .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

            // 5. Verificar se a vaga está disponível
            if (!vaga.getAvailable()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Vaga já está ocupada");
            }

            // 6. Atualizar a vaga para "indisponível"
            vaga.setAvailable(false);
            vagaRepository.save(vaga); // Atualiza a vaga no banco

            // 7. Criar e salvar a nova reserva
            Reserva reserva = new Reserva();
            reserva.setVeiculo(veiculo);
            reserva.setSpot(vaga);
            reserva.setStatus("RESERVADO");
            reserva.setCliente(cliente); // Certifique-se de que o cliente está associado à reserva

            // 8. Salvar a reserva
            Reserva reservaSalva = reservaRepository.save(reserva);

            return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalva);

        } catch (Exception e) {
            e.printStackTrace(); // Log do erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar reserva: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluirReserva")
public ResponseEntity<Void> excluirReserva(@RequestParam Long booking_id) {
    // Verifica se a reserva existe
    Optional<Reserva> reservaOptional = reservaRepository.findById(booking_id);
    if (!reservaOptional.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 caso não encontre a reserva
    }

    Reserva reserva = reservaOptional.get();
    
    // Recupera a vaga associada à reserva
    Vaga vaga = reserva.getSpot();
    
    // Verifica se a vaga existe
    if (vaga == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Caso não haja vaga associada à reserva
    }
    
    // Marca a vaga como disponível novamente
    vaga.setAvailable(true); // Ou qualquer outro status que você tenha para indicar que a vaga está disponível
    
    // Atualiza a vaga no banco de dados
    vagaRepository.save(vaga);  // Salva a vaga com o status atualizado
    
    // Exclui a reserva
    reservaRepository.deleteById(booking_id);

    return ResponseEntity.noContent().build(); // Retorna 204 (Sem conteúdo) após exclusão
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
