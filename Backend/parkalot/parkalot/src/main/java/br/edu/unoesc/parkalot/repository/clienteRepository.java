package br.edu.unoesc.parkalot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Cliente;
@Repository
public interface clienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf AND c.email = :email")
    Optional<Cliente> findByCpfAndEmail(String cpf, String email);

    Cliente findByCpf(String cpf);

    Cliente findByEmail(String email);
}
