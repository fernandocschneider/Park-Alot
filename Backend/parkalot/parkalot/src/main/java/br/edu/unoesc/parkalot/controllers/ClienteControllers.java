package br.edu.unoesc.parkalot.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.parkalot.model.Cliente;

/**
 * Controller para a entidade Cliente.
 * Responsável por gerenciar as operações CRUD e validações relacionadas ao
 * Cliente.
 * 
 * @author Jean Toral
 */
@RestController
public class ClienteControllers {

    @Autowired
    private br.edu.unoesc.parkalot.repository.clienteRepository clienteRepository;

    /**
     * Retorna uma lista de todos os clientes cadastrados.
     * 
     * @return ResponseEntity contendo a lista de clientes e o status HTTP OK.
     */
    @GetMapping(value = "listaClientes")
    @ResponseBody
    public ResponseEntity<List<Cliente>> listaCliente() {
        List<Cliente> clientes = clienteRepository.findAll();
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    /**
     * Salva um novo cliente no repositório.
     * 
     * @param cliente Objeto Cliente a ser salvo.
     * @return ResponseEntity contendo o cliente salvo e o status HTTP CREATED.
     */
    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        cliente = clienteRepository.save(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    /**
     * Exclui um cliente do repositório com base no ID fornecido.
     * 
     * @param idclient ID do cliente a ser excluído.
     * @return ResponseEntity com o status HTTP OK.
     */
    @DeleteMapping(value = "deleteCliente")
    @ResponseBody
    public ResponseEntity<Cliente> delete(@RequestParam Long idclient) {
        clienteRepository.deleteById(idclient);
        return new ResponseEntity<Cliente>(HttpStatus.OK);
    }

    /**
     * Atualiza as informações de um cliente existente.
     * 
     * @param cliente Objeto Cliente com as novas informações.
     * @return ResponseEntity contendo o cliente atualizado e o status HTTP OK,
     *         ou uma mensagem de erro se o ID não for informado.
     */
    @PutMapping(value = "atualizarCliente")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Cliente cliente) {
        if (cliente.getId() == null) {
            return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
        }
        cliente = clienteRepository.saveAndFlush(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    /**
     * Verifica se um CPF já está cadastrado.
     * 
     * @param cpf CPF do cliente a ser verificado.
     * @return ResponseEntity contendo true se o CPF existir, false caso contrário,
     *         com status HTTP OK.
     */
    @GetMapping(value = "verificarCPF")
    @ResponseBody
    public ResponseEntity<Boolean> verificarCPF(@RequestParam(name = "cpf") String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    /**
     * Verifica se um e-mail já está cadastrado.
     * 
     * @param email E-mail do cliente a ser verificado.
     * @return ResponseEntity contendo true se o e-mail existir, false caso
     *         contrário,
     *         com status HTTP OK.
     */
    @GetMapping(value = "verificarEmail")
    @ResponseBody
    public ResponseEntity<Boolean> verificarEmail(@RequestParam(name = "email") String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    /**
     * Verifica se um login com CPF e e-mail é válido.
     * 
     * @param cliente Objeto Cliente contendo CPF e e-mail.
     * @return ResponseEntity com uma mensagem de sucesso se o login for válido,
     *         ou uma mensagem de erro com status HTTP UNAUTHORIZED caso contrário.
     */
    @PostMapping(value = "verificarLogin")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> verificarLogin(@RequestBody Cliente cliente) {
        // Verifica se o cliente existe no banco com o CPF e email fornecidos
        Optional<Cliente> clienteOptional = clienteRepository.findByCpfAndEmail(cliente.getCpf(), cliente.getEmail());

        Map<String, Object> responseMap = new HashMap<>();

        if (clienteOptional.isPresent()) {
            // Cliente foi encontrado, recupera as informações
            Cliente clienteEncontrado = clienteOptional.get();

            // Adiciona o userId (ID do cliente) à resposta
            responseMap.put("status", "success");
            responseMap.put("userId", clienteEncontrado.getId()); // Inclui o userId na resposta
            responseMap.put("redirectUrl", "http://localhost:8080/Frontend/VAGAS/index.html");

            // Retorna a resposta com status 200 OK
            return ResponseEntity.ok(responseMap);
        } else {
            // Cliente não encontrado, retorna erro de login inválido
            responseMap.put("status", "error");
            responseMap.put("message", "Login inválido. Verifique CPF e/ou email.");
            return ResponseEntity.status(401).body(responseMap);
        }
    }

}
