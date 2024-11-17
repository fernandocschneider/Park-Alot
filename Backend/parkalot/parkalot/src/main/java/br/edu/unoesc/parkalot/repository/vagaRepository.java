package br.edu.unoesc.parkalot.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Vaga;

@Repository
public interface vagaRepository extends JpaRepository<Vaga, Long> {

        @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
                        "FROM Reserva r " +
                        "WHERE r.spot.id = :idVaga")
        boolean verificarReserva(@Param("idVaga") Long idVaga,
                        @Param("initialDate") Date initialDate,
                        @Param("finalDate") Date finalDate);

        List<Vaga> findByAvailable(boolean available);
}
