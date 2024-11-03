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
@Table(name = "Manutenção")
public class Manutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fix_id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "fix_description", length = 50, nullable = false)
    private String description;

    @NotNull
    @Column(name = "fix_date", nullable = false)
    private String date;

    @NotNull
    @Column(name = "fix_cost", nullable = false)
    private String cost;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", referencedColumnName = "worker_id", nullable = false)
    private Funcionario worker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Funcionario getOwner() {
        return worker;
    }

    public void setOwner(Funcionario owner) {
        this.worker = owner;
    }

    @Override
    public String toString() {
        return "Manutencao{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", cost='" + cost + '\'' +
                ", worker=" + worker +
                '}';
    }

}
