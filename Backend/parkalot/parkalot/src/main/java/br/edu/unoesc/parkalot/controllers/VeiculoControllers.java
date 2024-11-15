package br.edu.unoesc.parkalot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "criarVeiculo")
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        veiculo = veiculoRepository.save(veiculo);
        return new ResponseEntity<Veiculo>(veiculo, HttpStatus.CREATED);
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
