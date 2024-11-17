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
 * Classe que representa o registro de manutenção de veículos no sistema.
 * Contém informações sobre a descrição, data, custo e o funcionário responsável
 * pela manutenção.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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

    /**
     * Retorna o ID da manutenção.
     * 
     * @return o ID da manutenção.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da manutenção.
     * 
     * @param id o ID da manutenção.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna a descrição da manutenção.
     * 
     * @return a descrição da manutenção.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição da manutenção.
     * 
     * @param description a descrição da manutenção.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retorna a data em que a manutenção foi realizada.
     * 
     * @return a data da manutenção.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Define a data em que a manutenção foi realizada.
     * 
     * @param date a data da manutenção.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Retorna o custo da manutenção.
     * 
     * @return o custo da manutenção.
     */
    public Double getCost() {
        return cost;
    }

    /**
     * Define o custo da manutenção.
     * 
     * @param cost o custo da manutenção.
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * Retorna o funcionário responsável pela manutenção.
     * 
     * @return o funcionário responsável.
     */
    public Funcionario getWorker() {
        return worker;
    }

    /**
     * Define o funcionário responsável pela manutenção.
     * 
     * @param worker o funcionário responsável.
     */
    public void setWorker(Funcionario worker) {
        this.worker = worker;
    }

    /**
     * Retorna uma representação em string do objeto Manutencao.
     * 
     * @return representação em string do objeto Manutencao.
     */
    @Override
    public String toString() {
        return "Manutencao [id=" + id + ", description=" + description + ", date=" + date + ", cost=" + cost
                + ", worker=" + worker + "]";
    }

}
