package br.edu.unoesc.parkalot.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Classe que representa o pagamento de uma reserva no sistema de
 * estacionamento.
 * A tabela 'pagamento' contém informações sobre o custo do pagamento, data,
 * método de pagamento e a reserva associada a esse pagamento.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "pagamento")
public class Pagamento {

    /**
     * Identificador único do pagamento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false, unique = true)
    private Long id;

    /**
     * Custo do pagamento, correspondente ao valor pago pela reserva.
     */
    @Column(name = "payment_cost", nullable = false)
    private Double cost;

    /**
     * Data em que o pagamento foi realizado.
     */
    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    /**
     * Método de pagamento utilizado (ex: cartão de crédito, dinheiro, etc.).
     */
    @Column(name = "payment_method", length = 50, nullable = false)
    private String method;

    /**
     * A reserva associada a este pagamento.
     */
    @OneToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id", nullable = false)
    private Reserva reserva;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return "Pagamento [id=" + id + ", cost=" + cost + ", date=" + date + ", method=" + method + ", reserva="
                + reserva + "]";
    }

}
