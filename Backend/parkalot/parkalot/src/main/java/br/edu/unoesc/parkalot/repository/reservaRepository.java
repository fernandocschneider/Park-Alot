package br.edu.unoesc.parkalot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.unoesc.parkalot.model.Reserva;

@Repository
public interface reservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT COUNT(r) > 0 FROM Reserva r " +
            "WHERE r.spot.id = :spotId " +
            "AND r.status = 'ACTIVE'")
    boolean verificarReserva(
            @Param("spotId") Long spotId);

    RequestBody save(RequestBody reserva);

    RequestBody saveAndFlush(RequestBody reserva);

}
