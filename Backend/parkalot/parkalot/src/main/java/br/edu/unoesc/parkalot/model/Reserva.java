package br.edu.unoesc.parkalot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "booking_status", length = 20, nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    private Vaga spot;

    @ManyToOne
    @JoinColumn(name = "veicule_sign", referencedColumnName = "veicule_sign", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
    private Cliente cliente;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", status=" + status +
                ", spot=" + spot + ", veiculo=" + veiculo +
                ", cliente=" + cliente + "]";
    }
}
