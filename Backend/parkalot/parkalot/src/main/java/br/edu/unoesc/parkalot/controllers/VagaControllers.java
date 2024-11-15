package br.edu.unoesc.parkalot.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @GetMapping(value = "criarVaga")
    public ResponseEntity<Vaga> criarVaga(@RequestBody Vaga vaga) {
        vaga = vagaRepository.save(vaga);
        return new ResponseEntity<Vaga>(vaga, HttpStatus.CREATED);
    }

    /**
     * Atualiza uma vaga existente no repositório.
     * 
     * @param vaga Objeto Vaga com as informações atualizadas.
     * @return ResponseEntity contendo a vaga atualizada ou uma mensagem de erro se
     *         o ID não for informado.
     */
    @GetMapping(value = "atualizarVaga")
    public ResponseEntity<?> atualizarVaga(@RequestBody Vaga vaga) {
        if (vaga.getId() == null) {
            return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
        }
        vaga = vagaRepository.saveAndFlush(vaga);
        return new ResponseEntity<Vaga>(vaga, HttpStatus.OK);
    }

    /**
     * Verifica se uma vaga está reservada em um determinado intervalo de datas.
     * 
     * @param idVaga      ID da vaga a ser verificada.
     * @param initialDate Data inicial da reserva.
     * @param finalDate   Data final da reserva.
     * @return true se a vaga estiver reservada no intervalo especificado, false
     *         caso contrário.
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END" +
            "FROM reserva r " +
            "WHERE r.vaga.id = :idVaga " +
            "AND r.vaga.available = 'true'" +
            "AND (" +
            "(r.initialDate <= :finalDate AND r.finalDate >= :initialDate)" +
            ")")
    boolean verificarReserva(
            @Param("idVaga") Long idVaga,
            @Param("initialDate") Date initialDate,
            @Param("finalDate") Date finalDate) {
        return verificarReserva(idVaga, initialDate, finalDate);
    }
}
