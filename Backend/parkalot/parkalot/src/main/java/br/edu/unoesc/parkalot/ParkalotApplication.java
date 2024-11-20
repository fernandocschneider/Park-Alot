package br.edu.unoesc.parkalot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Classe principal que inicializa a aplicação Spring Boot.
 * Esta classe configura o contexto de aplicação e inicia o servidor da
 * aplicação.
 * 
 * A anotação {@link SpringBootApplication} é utilizada para configurar
 * automaticamente a aplicação Spring Boot.
 * A anotação {@link EntityScan} é usada para informar ao Spring onde encontrar
 * as classes de entidade JPA, que são as
 * representações das tabelas no banco de dados. Neste caso, as entidades estão
 * no pacote {@code br.edu.unoesc.parkalot.model}.
 * 
 * @author Jean Toral
 */
@EntityScan("br.edu.unoesc.parkalot.model")
@SpringBootApplication
public class ParkalotApplication {

	/**
	 * O método principal que inicia a aplicação Spring Boot.
	 * Este método dispara a execução da aplicação, configurando o contexto de
	 * Spring e iniciando o servidor.
	 * 
	 * @param args Argumentos da linha de comando (não usados diretamente aqui).
	 */
	public static void main(String[] args) {
		SpringApplication.run(ParkalotApplication.class, args);
	}
}