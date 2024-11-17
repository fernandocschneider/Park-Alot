package br.edu.unoesc.parkalot.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Vaga;

/**
 * Repositório de acesso aos dados da entidade Vaga.
 * Extende o JpaRepository para facilitar a comunicação com o banco de dados.
 * 
 * @author Jean Toral
 */
@Repository
public interface vagaRepository extends JpaRepository<Vaga, Long> {

        /**
         * Verifica se existe alguma reserva para uma vaga em um intervalo de datas
         * específico.
         * 
         * @param idVaga      o id da vaga de estacionamento.
         * @param initialDate a data inicial do intervalo.
         * @param finalDate   a data final do intervalo.
         * @return verdadeiro se existir uma reserva para a vaga no intervalo fornecido,
         *         falso caso contrário.
         */
        @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
                        "FROM Reserva r " +
                        "WHERE r.spot.id = :idVaga " +
                        "AND r.entryDate <= :finalDate " +
                        "AND r.leaveDate >= :initialDate")
        boolean verificarReserva(@Param("idVaga") Long idVaga,
                        @Param("initialDate") Date initialDate,
                        @Param("finalDate") Date finalDate);

        /**
         * Encontra todas as vagas com um status de disponibilidade especificado.
         * 
         * @param available se a vaga está disponível ou não.
         * @return uma lista de vagas com o status de disponibilidade especificado.
         */
        List<Vaga> findByAvailable(boolean available);
}
