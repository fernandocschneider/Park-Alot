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

/**
 * Controlador responsável por gerenciar a relação entre carro e vaga.
 * 
 * @author Jean Toral
 */
@RestController
public class RelacionarCarroeVaga {

    @Autowired
    private veiculoRepository veiculoRepository;

    @Autowired
    private vagaRepository vagaRepository;

    @Autowired
    private reservaRepository reservaRepository;

    /**
     * Endpoint para realizar o relacionamento entre um carro e uma vaga.
     * O método verifica se o carro e a vaga existem, se a vaga está disponível, e
     * realiza
     * a reserva associando o carro à vaga.
     * 
     * @param dados um mapa contendo os IDs do carro e da vaga a serem relacionados.
     * @return uma resposta com o status da operação, contendo a reserva criada em
     *         caso de sucesso
     *         ou uma mensagem de erro em caso de falha.
     */
    @PostMapping(value = "relacionarCarroVaga")
    public ResponseEntity<?> relacionarCarroVaga(@RequestBody Map<String, Long> dados) {
        Long carroId = dados.get("carroId");
        Long vagaId = dados.get("vagaId");

        // Recupera o veículo e a vaga pelos IDs fornecidos
        Veiculo veiculo = veiculoRepository.findById(carroId).orElse(null);
        Vaga vaga = vagaRepository.findById(vagaId).orElse(null);

        // Verifica se o veículo ou a vaga não foram encontrados
        if (veiculo == null || vaga == null) {
            return new ResponseEntity<String>("Carro ou Vaga não encontrados", HttpStatus.NOT_FOUND);
        }

        // Verifica se a vaga está disponível
        if (!vaga.getAvailable()) {
            return new ResponseEntity<String>("Vaga já ocupada", HttpStatus.CONFLICT);
        }

        // Cria a reserva associando o veículo e a vaga
        Reserva reserva = new Reserva();
        reserva.setVeiculo(veiculo);
        reserva.setSpot(vaga);
        reserva.setStatus("Reservada");

        // Atualiza o status da vaga para indisponível
        vaga.setAvailable(false);
        vagaRepository.save(vaga);

        // Salva a reserva no banco de dados
        reservaRepository.save(reserva);

        // Retorna a reserva criada com status HTTP 201 (Created)
        return new ResponseEntity<Reserva>(reserva, HttpStatus.CREATED);
    }
}
