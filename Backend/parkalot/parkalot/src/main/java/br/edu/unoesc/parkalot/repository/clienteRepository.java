package br.edu.unoesc.parkalot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Cliente;

/**
 * Interface que fornece métodos para acessar e manipular dados de clientes no banco de dados.
 * Estende a interface JpaRepository, fornecendo operações básicas de CRUD (Create, Read, Update, Delete).
 * 
 * @author Jean Toral
 */
@Repository
public interface clienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Retorna um cliente baseado no CPF e no e-mail.
     * 
     * @param cpf o CPF do cliente.
     * @param email o e-mail do cliente.
     * @return um Optional contendo o cliente, se encontrado.
     */
    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf AND c.email = :email")
    Optional<Cliente> findByCpfAndEmail(String cpf, String email);

    /**
     * Retorna um cliente baseado no CPF.
     * 
     * @param cpf o CPF do cliente.
     * @return o cliente encontrado.
     */
    Cliente findByCpf(String cpf);

    /**
     * Retorna um cliente baseado no e-mail.
     * 
     * @param email o e-mail do cliente.
     * @return o cliente encontrado.
     */
    Cliente findByEmail(String email);
}
