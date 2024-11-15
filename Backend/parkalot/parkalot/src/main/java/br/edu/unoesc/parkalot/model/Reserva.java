package br.edu.unoesc.parkalot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Classe que representa uma reserva de vaga para um veículo no estacionamento.
 * A tabela 'reserva' contém informações sobre o status da reserva,
 * a vaga associada e o veículo que fez a reserva.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "reserva")
public class Reserva {

    /**
     * Identificador único da reserva.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false, unique = true)
    private Long id;

    /**
     * Status da reserva (ex: "confirmada", "pendente", "finalizada").
     */
    @Column(name = "booking_status", length = 20, nullable = false)
    private String status;

    /**
     * A vaga associada à reserva.
     */
    @OneToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    private Vaga spot;

    /**
     * O veículo que fez a reserva.
     */
    @OneToOne
    @JoinColumn(name = "veicule_sign", referencedColumnName = "veicule_sign", nullable = false)
    private Veiculo veiculo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", initialDate=" +
                ", status=" + status + ", spot=" + spot +
                ", veiculo=" + veiculo + "]";
    }

}
