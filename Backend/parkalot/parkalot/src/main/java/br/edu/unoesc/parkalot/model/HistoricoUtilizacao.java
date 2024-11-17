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

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "historico_utilizacao")
public class HistoricoUtilizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "history_entry_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date entryDate;

    @Column(name = "history_leave_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date leaveDate;

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

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
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
        return "HistoricoUtilizacao [id=" + id + ", entryDate=" + entryDate + ", leaveDate=" + leaveDate + ", spot="
                + spot + ", veiculo=" + veiculo + "]";
    }

}
