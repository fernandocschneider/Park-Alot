package br.edu.unoesc.parkalot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.unoesc.parkalot.model.Reserva;

/**
 * Interface que fornece métodos para acessar e manipular dados de reservas no
 * banco de dados.
 * Estende a interface JpaRepository, fornecendo operações básicas de CRUD
 * (Create, Read, Update, Delete).
 * Além disso, inclui métodos personalizados para verificar reservas ativas.
 * 
 * @author Jean Toral
 */
@Repository
public interface reservaRepository extends JpaRepository<Reserva, Long> {

        /**
         * Verifica se existe uma reserva ativa para um determinado espaço (vaga).
         * 
         * @param spotId o ID do espaço (vaga) para verificar se há uma reserva ativa.
         * @return true se houver uma reserva ativa para o espaço, caso contrário,
         *         false.
         */
        @Query("SELECT COUNT(r) > 0 FROM Reserva r " +
                        "WHERE r.spot.id = :spotId " +
                        "AND r.status = 'ACTIVE'")
        boolean verificarReserva(@Param("spotId") Long spotId);

        /**
         * Método para salvar uma reserva. Este método está incorretamente utilizando o
         * tipo RequestBody,
         * que é geralmente utilizado em controllers, não em repositórios.
         * Este código deveria ser ajustado para usar o tipo correto, que é o tipo de
         * entidade Reserva.
         * 
         * @param reserva a reserva a ser salva.
         * @return a reserva salva.
         */
        RequestBody save(RequestBody reserva);

        /**
         * Método para salvar e fazer flush de uma reserva.
         * Similar ao método save, mas com flush imediato no banco de dados.
         * Também utiliza RequestBody de forma incorreta e deveria ser ajustado para o
         * tipo correto.
         * 
         * @param reserva a reserva a ser salva e imediatamente persistida.
         * @return a reserva salva.
         */
        RequestBody saveAndFlush(RequestBody reserva);
}
