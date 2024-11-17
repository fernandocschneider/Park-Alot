package br.edu.unoesc.parkalot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
                        "AND r.status = 'ACTIVE'") // Verifique se o status é 'ACTIVE' ou conforme necessário
        boolean verificarReserva(@Param("spotId") Long spotId);

        List<Reserva> findByClienteId(Long clientId);
}
