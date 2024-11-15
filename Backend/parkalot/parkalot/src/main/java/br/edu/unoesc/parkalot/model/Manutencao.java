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
 * Classe que representa o registro de uma manutenção realizada no sistema de
 * estacionamento.
 * A tabela 'manutencao' contém informações sobre a manutenção realizada, como
 * descrição,
 * data, custo e o trabalhador responsável.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "manutencao")
public class Manutencao {

    /**
     * Identificador único da manutenção.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fix_id", nullable = false, unique = true)
    private Long id;

    /**
     * Descrição da manutenção realizada.
     */
    @Column(name = "fix_description", length = 50, nullable = false)
    private String description;

    /**
     * Data em que a manutenção foi realizada.
     */
    @Column(name = "fix_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    /**
     * Custo da manutenção realizada.
     */
    @Column(name = "fix_cost", nullable = false)
    private Double cost;

    /**
     * Funcionário responsável pela manutenção.
     */
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
