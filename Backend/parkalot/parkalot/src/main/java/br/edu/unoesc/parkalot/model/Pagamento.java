package br.edu.unoesc.parkalot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "payment_cost", nullable = false)
    private String cost;

    @NotNull
    @Column(name = "payment_date", nullable = false)
    private String date;

    @NotNull
    @Column(name = "payment_method", length = 50, nullable = false)
    private String method;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id", nullable = false)
    private Reserva booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Reserva getReserva() {
        return booking;
    }

    public void setReserva(Reserva reserva) {
        this.booking = reserva;
    }

    @Override
    public String toString() {
        return "Pagamento{" + "id=" + id + ", cost='" + cost + '\'' + ", date='" + date + '\'' + ", method='" + method
                + '\'' + ", reserva=" + booking + '}';
    }
}
