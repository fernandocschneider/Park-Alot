package br.edu.unoesc.parkalot.controllers;

import java.util.List;

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
public class GreetingsController {
    // @GetMapping("/")
    // public String index() {
    //     return "Hello, World!";
    // }

    // @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    // @ResponseStatus(HttpStatus.OK)
    // public String texto(@PathVariable String name) {
    //     return "Hello, " + name + " !";
    // }

    @Autowired
    private br.edu.unoesc.parkalot.repository.clienteRepository clienteRepository;

    // @RequestMapping(value = "testeGravar/{nome}", method = RequestMethod.GET)
    // @ResponseStatus(HttpStatus.OK)
    // public String testeGravar(@PathVariable String nome) {
    //     Cliente cliente = new Cliente();
    //     cliente.setName(nome);
    //     cliente.setCpf("12345678901");
    //     cliente.setEmail("teste@teste.com");
    //     cliente.setPhone("1234567890");
    //     cliente.setSex("M");
    //     cliente.setAge(12);
    //     clienteRepository.save(cliente);
    //     return "Gravado";
    // }

    @GetMapping(value = "listatodos")
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

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<Cliente> delete(@RequestParam Long idclient) {
        clienteRepository.deleteById(idclient);
        return new ResponseEntity<Cliente>(HttpStatus.OK);
    }

    @GetMapping(value = "buscarcliente")
    @ResponseBody
    public ResponseEntity<Cliente> buscarcliente(@RequestParam(name= "idclient") Long idclient) {
        Cliente cliente = clienteRepository.findById(idclient).get();
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Cliente cliente) {
        if(cliente.getId() == null) {
            return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
        }
        cliente = clienteRepository.saveAndFlush(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @GetMapping(value = "verificarCPF")
    @ResponseBody
    public ResponseEntity<Boolean> verificarCPF(@RequestParam(name = "cpf") String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        return ResponseEntity.ok(cliente != null);
    }

    @GetMapping(value = "verificarEmail")
    @ResponseBody 
    public ResponseEntity<Boolean> verificarEmail(@RequestParam(name = "email") String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        return ResponseEntity.ok(cliente != null);
    }
}