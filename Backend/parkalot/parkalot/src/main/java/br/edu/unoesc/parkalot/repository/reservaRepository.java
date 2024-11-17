package br.edu.unoesc.parkalot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Reserva;

/**
 * Repositório de acesso aos dados da entidade Reserva.
 * Extende o JpaRepository para facilitar a comunicação com o banco de dados.
 * 
 * @author Jean Toral
 */
@Repository
public interface reservaRepository extends JpaRepository<Reserva, Long> {

        /**
         * Verifica se existe uma reserva ativa para o estacionamento (vaga)
         * especificado.
         * 
         * @param spotId o id da vaga de estacionamento.
         * @return verdadeiro se existir uma reserva ativa para a vaga, falso caso
         *         contrário.
         */
        @Query("SELECT COUNT(r) > 0 FROM Reserva r " +
                        "WHERE r.spot.id = :spotId " +
                        "AND r.status = 'ACTIVE'")
        boolean verificarReserva(@Param("spotId") Long spotId);

        /**
         * Encontra todas as reservas de um cliente específico.
         * 
         * @param clientId o id do cliente.
         * @return uma lista de reservas associadas ao cliente.
         */
        List<Reserva> findByClienteId(Long clientId);
}
