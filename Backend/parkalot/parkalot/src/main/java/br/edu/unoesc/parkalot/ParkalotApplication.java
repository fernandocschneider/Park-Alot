package br.edu.unoesc.parkalot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("br.edu.unoesc.parkalot.model")
@SpringBootApplication
public class ParkalotApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParkalotApplication.class, args);
	}
}