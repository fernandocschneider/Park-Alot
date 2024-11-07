package br.edu.unoesc.parkalot.model;

import java.util.Date;

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
@Table(name = "manutencao")
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fix_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "fix_description", length = 50, nullable = false)
    private String description;

    @Column(name = "fix_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "fix_cost", nullable = false)
    private Double cost;

    @OneToOne
    @JoinColumn(name = "worker_cpf", referencedColumnName = "worker_cpf", nullable = false)
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Funcionario getWorker() {
        return worker;
    }

    public void setWorker(Funcionario worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "Manutencao [id=" + id + ", description=" + description + ", date=" + date + ", cost=" + cost
                + ", worker=" + worker + "]";
    }

}
