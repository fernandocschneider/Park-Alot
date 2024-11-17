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

/**
 * Classe que representa uma reserva de vaga de estacionamento no sistema.
 * Contém informações sobre o status da reserva, a vaga, o veículo e o cliente
 * associados.
 * 
 * @author Jean Toral
 */
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

    /**
     * Retorna o ID da reserva.
     * 
     * @return o ID da reserva.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da reserva.
     * 
     * @param id o ID da reserva.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o status da reserva.
     * 
     * @return o status da reserva.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status da reserva.
     * 
     * @param status o status da reserva.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retorna a vaga associada à reserva.
     * 
     * @return a vaga associada.
     */
    public Vaga getSpot() {
        return spot;
    }

    /**
     * Define a vaga associada à reserva.
     * 
     * @param spot a vaga associada.
     */
    public void setSpot(Vaga spot) {
        this.spot = spot;
    }

    /**
     * Retorna o veículo associado à reserva.
     * 
     * @return o veículo associado.
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Define o veículo associado à reserva.
     * 
     * @param veiculo o veículo associado.
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Retorna o cliente que fez a reserva.
     * 
     * @return o cliente da reserva.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente da reserva.
     * 
     * @param cliente o cliente da reserva.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna uma representação em string do objeto Reserva.
     * 
     * @return representação em string do objeto Reserva.
     */
    @Override
    public String toString() {
        return "Reserva [id=" + id + ", status=" + status +
                ", spot=" + spot + ", veiculo=" + veiculo +
                ", cliente=" + cliente + "]";
    }
}
