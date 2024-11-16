package br.edu.unoesc.parkalot.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Vaga;

/**
 * Interface que fornece métodos para acessar e manipular dados das vagas no
 * banco de dados.
 * Estende a interface JpaRepository, fornecendo operações básicas de CRUD
 * (Create, Read, Update, Delete).
 * 
 * @author Jean Toral
 */
@Repository
public interface vagaRepository extends JpaRepository<Vaga, Long> {
    /**
     * Verifica se a vaga está reservada no intervalo de datas especificado.
     * 
     * @param idVaga      ID da vaga.
     * @param initialDate Data inicial da reserva.
     * @param finalDate   Data final da reserva.
     * @return true se a vaga estiver reservada no intervalo, false caso contrário.
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reserva r " +
            "WHERE r.spot.id = :idVaga")
    boolean verificarReserva(@Param("idVaga") Long idVaga,
            @Param("initialDate") Date initialDate,
            @Param("finalDate") Date finalDate);
}
