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

/**
 * Controller responsável pelo gerenciamento das vagas de estacionamento.
 * Inclui operações CRUD e a verificação de reservas em intervalos de datas.
 * 
 * @author Jean Toral
 */
@RestController
public class VagaControllers {

    @Autowired
    private br.edu.unoesc.parkalot.repository.vagaRepository vagaRepository;

    /**
     * Retorna a lista de todas as vagas de estacionamento cadastradas.
     * 
     * @return ResponseEntity contendo a lista de vagas e o status HTTP OK.
     */
    @GetMapping(value = "listaVagas")
    public ResponseEntity<List<Vaga>> listaVagas() {
        List<Vaga> vagas = vagaRepository.findAll();
        return new ResponseEntity<List<Vaga>>(vagas, HttpStatus.OK);
    }

    /**
     * Cria uma nova vaga de estacionamento e a salva no repositório.
     * 
     * @param vaga Objeto Vaga a ser criada e salva.
     * @return ResponseEntity contendo a vaga criada e o status HTTP CREATED.
     */
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
        // Utiliza o método do repositório para buscar vagas disponíveis (available =
        // true)
        List<Vaga> vagasDisponiveis = vagaRepository.findByAvailable(true);

        // Verifica se a lista de vagas disponíveis está vazia
        if (vagasDisponiveis.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna status 204 se não houver vagas disponíveis
        }

        // Retorna a lista de vagas disponíveis com status 200 (OK)
        return ResponseEntity.ok(vagasDisponiveis);
    }
}
