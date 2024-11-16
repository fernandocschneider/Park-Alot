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
 * A tabela 'vaga' contém informações sobre a disponibilidade, o tempo
 * e a localização da vaga.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "vaga")
public class Vaga {

    /**
     * Identificador único da vaga.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id", nullable = false, unique = true)
    private Long id;

    /**
     * Indicador de disponibilidade da vaga (true se disponível, false caso
     * contrário).
     */
    @Column(name = "spot_available", length = 1, nullable = false)
    private boolean available;

    /**
     * Tempo máximo permitido para estacionamento na vaga.
     */
    @Column(name = "spot_time", nullable = false)
    private int time;

    /**
     * Localização ou descrição do local da vaga.
     */
    @Column(name = "spot_local", length = 50, nullable = false)
    private String local;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Vaga [id=" + id + ", available=" + available + ", time=" + time + ", local=" + local + "]";
    }

}
