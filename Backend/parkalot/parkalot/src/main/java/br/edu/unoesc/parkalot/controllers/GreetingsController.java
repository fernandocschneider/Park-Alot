package br.edu.unoesc.parkalot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.parkalot.model.Cliente;

@RestController
public class GreetingsController {
	@GetMapping("/")
	public String index() {
		return "Hello, World!";
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String texto(@PathVariable String name) {
		return "Hello, " + name + " !";
	}

	@Autowired
	private br.edu.unoesc.parkalot.repository.clienteRepository clienteRepository;

	@RequestMapping(value = "testeGravar/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String testeGravar(@PathVariable String nome) {
		Cliente cliente = new Cliente();
		cliente.setName(nome);
        cliente.setCpf("12345678901");
        cliente.setEmail("teste@teste.com");
        cliente.setPhone(1234567890);
        cliente.setSex("M");
        cliente.setAge(12);
		clienteRepository.save(cliente);
		return "Gravado";
	}

}