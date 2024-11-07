package br.edu.unoesc.parkalot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "spot_available", length = 1, nullable = false)
    private String available;

    @Column(name = "spot_time", nullable = false)
    private String time;

    @Column(name = "spot_local", length = 50, nullable = false)
    private String local;

    @OneToOne
    @JoinColumn(name = "fix_id", referencedColumnName = "fix_id", nullable = false)
    private Manutencao maintenance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Manutencao getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Manutencao maintenance) {
        this.maintenance = maintenance;
    }

    @Override
    public String toString() {
        return "Vaga [id=" + id + ", available=" + available + ", time=" + time + ", local=" + local + ", maintenance="
                + maintenance + "]";
    }

}
