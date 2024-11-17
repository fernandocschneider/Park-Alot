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
 * Classe que representa o registro de pagamento de uma reserva no sistema.
 * Contém informações sobre o custo, a data, o método de pagamento e a reserva
 * associada.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "payment_cost", nullable = false)
    private Double cost;

    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "payment_method", length = 50, nullable = false)
    private String method;

    @OneToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id", nullable = false)
    private Reserva reserva;

    /**
     * Retorna o ID do pagamento.
     * 
     * @return o ID do pagamento.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do pagamento.
     * 
     * @param id o ID do pagamento.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o custo do pagamento.
     * 
     * @return o custo do pagamento.
     */
    public Double getCost() {
        return cost;
    }

    /**
     * Define o custo do pagamento.
     * 
     * @param cost o custo do pagamento.
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * Retorna a data do pagamento.
     * 
     * @return a data do pagamento.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Define a data do pagamento.
     * 
     * @param date a data do pagamento.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Retorna o método de pagamento utilizado.
     * 
     * @return o método de pagamento.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Define o método de pagamento utilizado.
     * 
     * @param method o método de pagamento.
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Retorna a reserva associada ao pagamento.
     * 
     * @return a reserva associada.
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * Define a reserva associada ao pagamento.
     * 
     * @param reserva a reserva associada.
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * Retorna uma representação em string do objeto Pagamento.
     * 
     * @return representação em string do objeto Pagamento.
     */
    @Override
    public String toString() {
        return "Pagamento [id=" + id + ", cost=" + cost + ", date=" + date + ", method=" + method + ", reserva="
                + reserva + "]";
    }
}
