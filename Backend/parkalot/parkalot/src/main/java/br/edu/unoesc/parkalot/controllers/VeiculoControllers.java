package br.edu.unoesc.parkalot.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.parkalot.model.Cliente;
import br.edu.unoesc.parkalot.model.Veiculo;

/**
 * Controlador responsável pela gestão dos veículos registrados no sistema.
 * Fornece endpoints para listar, criar, atualizar e excluir veículos.
 * 
 * @author Jean Toral
 */
@RestController
public class VeiculoControllers {

    @Autowired
    private br.edu.unoesc.parkalot.repository.veiculoRepository veiculoRepository;

    @Autowired
    private br.edu.unoesc.parkalot.repository.clienteRepository clienteRepository;

    /**
     * Endpoint para listar todos os veículos registrados no sistema.
     * 
     * @return uma lista de veículos com status HTTP 200 OK.
     */
    @GetMapping(value = "listaVeiculos")
    public ResponseEntity<List<Veiculo>> listaVeiculos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return new ResponseEntity<List<Veiculo>>(veiculos, HttpStatus.OK);
    }

    /**
     * Endpoint para criar um novo veículo no sistema.
     * 
     * @param veiculoData um mapa contendo os dados do veículo a ser criado.
     * @return o veículo criado com status HTTP 201 CREATED, ou um status HTTP 400
     *         BAD REQUEST se o cliente não for encontrado.
     */
    @PostMapping(value = "/criarVeiculo")
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Map<String, Object> veiculoData) {
        Long clientId = Long.valueOf((String) veiculoData.get("client_id"));

        // Verifica se o cliente existe no sistema
        Cliente cliente = clienteRepository.findById(clientId).orElse(null);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Cria e configura o novo veículo
        Veiculo veiculo = new Veiculo();
        veiculo.setBrand((String) veiculoData.get("brand"));
        veiculo.setColor((String) veiculoData.get("color"));
        veiculo.setModel((String) veiculoData.get("model"));
        veiculo.setPlate((String) veiculoData.get("plate"));
        veiculo.setYear((String) veiculoData.get("year"));
        veiculo.setOwner(cliente);

        veiculo = veiculoRepository.save(veiculo);

        return new ResponseEntity<>(veiculo, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualizar as informações de um veículo existente no sistema.
     * 
     * @param veiculo o veículo com as informações a serem atualizadas.
     * @return o veículo atualizado com status HTTP 200 OK, ou uma mensagem de erro
     *         caso o ID não seja informado.
     */
    @GetMapping(value = "atualizarVeiculo")
    public ResponseEntity<?> atualizarVeiculo(@RequestBody Veiculo veiculo) {
        if (veiculo.getId() == null) {
            return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
        }
        veiculo = veiculoRepository.saveAndFlush(veiculo);
        return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
    }

    /**
     * Endpoint para excluir um veículo do sistema.
     * 
     * @param idveiculo o ID do veículo a ser excluído.
     * @return um status HTTP 200 OK após a exclusão do veículo.
     */
    @GetMapping(value = "deleteVeiculo")
    public ResponseEntity<Veiculo> deleteVeiculo(@RequestParam Long idveiculo) {
        veiculoRepository.deleteById(idveiculo);
        return new ResponseEntity<Veiculo>(HttpStatus.OK);
    }
}
