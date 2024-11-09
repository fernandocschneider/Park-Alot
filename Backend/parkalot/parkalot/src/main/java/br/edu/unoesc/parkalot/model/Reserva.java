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

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false, unique = true)
    private Long id;

    // @Column(name = "booking_initial_date", nullable = false)
    // @Temporal(TemporalType.DATE)
    // private Date initialDate;

    // @Column(name = "booking_final_date", nullable = false)
    // @Temporal(TemporalType.DATE)
    // private Date finalDate;

    @Column(name = "booking_status", length = 20, nullable = false)
    private String status;

    @OneToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    private Vaga spot;

    @OneToOne
    @JoinColumn(name = "veicule_sign", referencedColumnName = "veicule_sign", nullable = false)
    private Veiculo veiculo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // public Date getInitialDate() {
    //     return initialDate;
    // }

    // public void setInitialDate(Date initialDate) {
    //     this.initialDate = initialDate;
    // }

    // public Date getFinalDate() {
    //     return finalDate;
    // }

    // public void setFinalDate(Date finalDate) {
    //     this.finalDate = finalDate;
    // }

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
        /*initialDate + ", finalDate=" + finalDate + */
        ", status=" + status + ", spot=" + spot + 
        ", veiculo=" + veiculo + "]";
    }

}
