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
 * Controlador responsável pela gestão das vagas de estacionamento.
 * Fornece endpoints para listar vagas, verificar reservas e listar vagas
 * disponíveis.
 * 
 * @author Jean Toral
 */
@RestController
public class VagaControllers {

    @Autowired
    private br.edu.unoesc.parkalot.repository.vagaRepository vagaRepository;

    /**
     * Endpoint para listar todas as vagas de estacionamento.
     * 
     * @return uma lista de vagas com status HTTP 200 OK.
     */
    @GetMapping(value = "listaVagas")
    public ResponseEntity<List<Vaga>> listaVagas() {
        List<Vaga> vagas = vagaRepository.findAll();
        return new ResponseEntity<List<Vaga>>(vagas, HttpStatus.OK);
    }

    /**
     * Endpoint para verificar se uma vaga está reservada em um intervalo de datas.
     * 
     * @param idVaga      o ID da vaga a ser verificada.
     * @param initialDate a data inicial do período a ser verificado.
     * @param finalDate   a data final do período a ser verificado.
     * @return um valor booleano indicando se a vaga está reservada ou não, com
     *         status HTTP 200 OK.
     */
    @GetMapping(value = "verificarReserva")
    public ResponseEntity<Boolean> verificarReserva(
            @RequestParam Long idVaga,
            @RequestParam Date initialDate,
            @RequestParam Date finalDate) {
        boolean isReserved = vagaRepository.verificarReserva(idVaga, initialDate, finalDate);
        return new ResponseEntity<>(isReserved, HttpStatus.OK);
    }

    /**
     * Endpoint para listar as vagas disponíveis (aquelas que estão marcadas como
     * "available").
     * 
     * @return uma lista de vagas disponíveis com status HTTP 200 OK.
     *         Caso não haja vagas disponíveis, retorna um status HTTP 204 No
     *         Content.
     */
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Vaga>> listarVagasDisponiveis() {
        List<Vaga> vagasDisponiveis = vagaRepository.findByAvailable(true);

        if (vagasDisponiveis.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(vagasDisponiveis);
    }
}
