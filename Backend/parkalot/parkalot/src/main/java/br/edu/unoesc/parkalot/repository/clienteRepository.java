package br.edu.unoesc.parkalot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Cliente;

/**
 * Repositório de acesso aos dados da entidade Cliente.
 * Extende o JpaRepository para facilitar a comunicação com o banco de dados.
 * 
 * @author Jean Toral
 */
@Repository
public interface clienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Encontra um cliente com base no CPF e email.
     * 
     * @param cpf   o CPF do cliente.
     * @param email o email do cliente.
     * @return um Optional contendo o cliente encontrado, caso exista.
     */
    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf AND c.email = :email")
    Optional<Cliente> findByCpfAndEmail(String cpf, String email);

    /**
     * Encontra um cliente com base no CPF.
     * 
     * @param cpf o CPF do cliente.
     * @return o cliente encontrado, caso exista.
     */
    Cliente findByCpf(String cpf);

    /**
     * Encontra um cliente com base no email.
     * 
     * @param email o email do cliente.
     * @return o cliente encontrado, caso exista.
     */
    Cliente findByEmail(String email);
}
