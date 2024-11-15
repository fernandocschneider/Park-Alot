package br.edu.unoesc.parkalot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Funcionario;

/**
 * Interface que fornece métodos para acessar e manipular dados de funcionários no banco de dados.
 * Estende a interface JpaRepository, fornecendo operações básicas de CRUD (Create, Read, Update, Delete).
 * 
 * @author Jean Toral
 */
@Repository
public interface funcionarioRepository extends JpaRepository<Funcionario, Long> {

}
