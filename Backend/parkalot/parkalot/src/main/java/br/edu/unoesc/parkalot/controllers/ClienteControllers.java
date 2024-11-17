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

@RestController
public class ClienteControllers {

    @Autowired
    private br.edu.unoesc.parkalot.repository.clienteRepository clienteRepository;

    @GetMapping(value = "listaClientes")
    @ResponseBody
    public ResponseEntity<List<Cliente>> listaCliente() {
        List<Cliente> clientes = clienteRepository.findAll();
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        cliente = clienteRepository.save(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deleteCliente")
    @ResponseBody
    public ResponseEntity<Cliente> delete(@RequestParam Long idclient) {
        clienteRepository.deleteById(idclient);
        return new ResponseEntity<Cliente>(HttpStatus.OK);
    }

    @PutMapping(value = "atualizarCliente")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Cliente cliente) {
        if (cliente.getId() == null) {
            return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
        }
        cliente = clienteRepository.saveAndFlush(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @GetMapping(value = "verificarCPF")
    @ResponseBody
    public ResponseEntity<Boolean> verificarCPF(@RequestParam(name = "cpf") String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping(value = "verificarEmail")
    @ResponseBody
    public ResponseEntity<Boolean> verificarEmail(@RequestParam(name = "email") String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping(value = "verificarLogin")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> verificarLogin(@RequestBody Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpfAndEmail(cliente.getCpf(), cliente.getEmail());

        Map<String, Object> responseMap = new HashMap<>();

        if (clienteOptional.isPresent()) {
            Cliente clienteEncontrado = clienteOptional.get();
            responseMap.put("status", "success");
            responseMap.put("userId", clienteEncontrado.getId());
            responseMap.put("redirectUrl", "http://localhost:8080/Frontend/VAGAS/index.html");
            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("status", "error");
            responseMap.put("message", "Login inválido. Verifique CPF e/ou email.");
            return ResponseEntity.status(401).body(responseMap);
        }
    }

}
