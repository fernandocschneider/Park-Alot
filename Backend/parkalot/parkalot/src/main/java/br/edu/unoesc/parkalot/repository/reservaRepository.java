package br.edu.unoesc.parkalot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Reserva;

@Repository
public interface reservaRepository extends JpaRepository<Reserva, Long> {

        @Query("SELECT COUNT(r) > 0 FROM Reserva r " +
                        "WHERE r.spot.id = :spotId " +
                        "AND r.status = 'ACTIVE'")
        boolean verificarReserva(@Param("spotId") Long spotId);

        List<Reserva> findByClienteId(Long clientId);
}
