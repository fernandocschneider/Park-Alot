package br.edu.unoesc.parkalot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Veiculo;

/**
 * Interface que fornece métodos para acessar e manipular dados dos veículos no banco de dados.
 * Estende a interface JpaRepository, fornecendo operações básicas de CRUD (Create, Read, Update, Delete).
 * 
 * Adicionalmente, oferece um método personalizado para buscar um veículo pelo número da placa.
 * 
 * @author Jean Toral
 */
@Repository
public interface veiculoRepository extends JpaRepository<Veiculo, Long> {

    /**
     * Método personalizado que encontra um veículo com base na placa.
     * 
     * @param plate A placa do veículo a ser buscado.
     * @return Um Optional contendo o veículo correspondente, caso encontrado, ou vazio caso não.
     */
    Optional<Veiculo> findByPlate(String plate);
}
