package br.edu.unoesc.parkalot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe que representa uma vaga de estacionamento no sistema.
 * Contém informações sobre a disponibilidade, tempo de uso e local da vaga.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "spot_available", length = 1, nullable = false)
    private boolean available;

    @Column(name = "spot_time", nullable = false)
    private int time;

    @Column(name = "spot_local", length = 50, nullable = false)
    private String local;

    /**
     * Retorna o ID da vaga.
     * 
     * @return o ID da vaga.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da vaga.
     * 
     * @param id o ID da vaga.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna a disponibilidade da vaga.
     * 
     * @return verdadeiro se a vaga estiver disponível, falso caso contrário.
     */
    public boolean getAvailable() {
        return available;
    }

    /**
     * Define a disponibilidade da vaga.
     * 
     * @param available verdadeiro para definir a vaga como disponível, falso caso
     *                  contrário.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Retorna o tempo de uso da vaga, em minutos.
     * 
     * @return o tempo de uso da vaga.
     */
    public int getTime() {
        return time;
    }

    /**
     * Define o tempo de uso da vaga.
     * 
     * @param time o tempo de uso da vaga, em minutos.
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Retorna o local da vaga no estacionamento.
     * 
     * @return o local da vaga.
     */
    public String getLocal() {
        return local;
    }

    /**
     * Define o local da vaga no estacionamento.
     * 
     * @param local o local da vaga.
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * Retorna uma representação em string do objeto Vaga.
     * 
     * @return representação em string do objeto Vaga.
     */
    @Override
    public String toString() {
        return "Vaga [id=" + id + ", available=" + available + ", time=" + time + ", local=" + local + "]";
    }
}
