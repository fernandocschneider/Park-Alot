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
 * Controller responsável pelo gerenciamento dos veículos.
 * Implementa operações CRUD para o gerenciamento dos veículos cadastrados no
 * sistema.
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
     * Retorna a lista de todos os veículos cadastrados.
     * 
     * @return ResponseEntity contendo a lista de veículos e o status HTTP OK.
     */
    @GetMapping(value = "listaVeiculos")
    public ResponseEntity<List<Veiculo>> listaVeiculos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return new ResponseEntity<List<Veiculo>>(veiculos, HttpStatus.OK);
    }

    /**
     * Cria um novo veículo e o salva no repositório.
     * 
     * @param veiculo Objeto Veiculo a ser criado e salvo.
     * @return ResponseEntity contendo o veículo criado e o status HTTP CREATED.
     */
    @PostMapping(value = "/criarVeiculo")
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Map<String, Object> veiculoData) {
        // Extrair o client_id do mapa de dados
        Long clientId = Long.valueOf((String) veiculoData.get("client_id"));

        // Buscar o cliente pelo clientId
        Cliente cliente = clienteRepository.findById(clientId).orElse(null);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retornar erro se o cliente não for encontrado
        }

        // Criar e preencher o objeto Veiculo
        Veiculo veiculo = new Veiculo();
        veiculo.setBrand((String) veiculoData.get("brand"));
        veiculo.setColor((String) veiculoData.get("color"));
        veiculo.setModel((String) veiculoData.get("model"));
        veiculo.setPlate((String) veiculoData.get("plate"));
        veiculo.setYear((String) veiculoData.get("year"));

        // Corrigir a associação do cliente com o veículo usando o campo 'owner'
        veiculo.setOwner(cliente);

        // Salvar o veículo no banco de dados
        veiculo = veiculoRepository.save(veiculo);

        return new ResponseEntity<>(veiculo, HttpStatus.CREATED);
    }

    /**
     * Atualiza um veículo existente no repositório.
     * 
     * @param veiculo Objeto Veiculo com os dados atualizados.
     * @return ResponseEntity contendo o veículo atualizado ou uma mensagem de erro
     *         se o ID não for informado.
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
     * Exclui um veículo com base no ID informado.
     * 
     * @param idveiculo ID do veículo a ser excluído.
     * @return ResponseEntity com o status HTTP OK.
     */
    @GetMapping(value = "deleteVeiculo")
    public ResponseEntity<Veiculo> deleteVeiculo(@RequestParam Long idveiculo) {
        veiculoRepository.deleteById(idveiculo);
        return new ResponseEntity<Veiculo>(HttpStatus.OK);
    }
}
