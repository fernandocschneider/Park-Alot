package br.edu.unoesc.parkalot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "booking_initial_date", nullable = false)
    private String initialDate;

    @NotNull
    @Column(name = "booking_final_date", nullable = false)
    private String finalDate;

    @NotNull
    @Column(name = "booking_status", length = 20, nullable = false)
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    private Vaga spot;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veicule_sign", referencedColumnName = "veicule_sign", nullable = false)
    private Veiculo vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vaga getSpot() {
        return spot;
    }

    public void setSpot(Vaga spot) {
        this.spot = spot;
    }

    public Veiculo getVehicle() {
        return vehicle;
    }

    public void setVehicle(Veiculo vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", initialDate='" + initialDate + '\'' +
                ", finalDate='" + finalDate + '\'' +
                ", status='" + status + '\'' +
                ", spot=" + spot +
                ", vehicle=" + vehicle +
                '}';
    }
}
