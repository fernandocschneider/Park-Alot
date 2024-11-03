package br.edu.unoesc.parkalot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Endereco;

@Repository
public interface enderecoRepository extends JpaRepository<Endereco, Long> {

}