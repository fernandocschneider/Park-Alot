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
@Table(name = "Historico_utilizacao")
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "history_entry_date", nullable = false)
    private String entryDate;

    @NotNull
    @Column(name = "history_leave_date", nullable = false)
    private String leaveDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    private Vaga spot;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "veicule_sign", referencedColumnName = "veicule_sign", nullable = false)
    private Veiculo vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
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
        return "Historico{" + "id=" + id + ", entryDate='" + entryDate + '\'' + ", leaveDate='" + leaveDate + '\''
                + ", spot=" + spot + ", vehicle=" + vehicle + '}';
    }
}
