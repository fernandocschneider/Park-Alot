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
 * Classe que representa o histórico de utilização de vagas por veículos no
 * sistema.
 * Contém informações sobre a data de entrada, data de saída, vaga e veículo
 * associados.
 * 
 * @author Jean Toral
 */
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

    /**
     * Retorna o ID do histórico de utilização.
     * 
     * @return o ID do histórico de utilização.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do histórico de utilização.
     * 
     * @param id o ID do histórico de utilização.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna a data de entrada do veículo na vaga.
     * 
     * @return a data de entrada.
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * Define a data de entrada do veículo na vaga.
     * 
     * @param entryDate a data de entrada.
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * Retorna a data de saída do veículo da vaga.
     * 
     * @return a data de saída.
     */
    public Date getLeaveDate() {
        return leaveDate;
    }

    /**
     * Define a data de saída do veículo da vaga.
     * 
     * @param leaveDate a data de saída.
     */
    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    /**
     * Retorna a vaga associada ao histórico de utilização.
     * 
     * @return a vaga associada.
     */
    public Vaga getSpot() {
        return spot;
    }

    /**
     * Define a vaga associada ao histórico de utilização.
     * 
     * @param spot a vaga associada.
     */
    public void setSpot(Vaga spot) {
        this.spot = spot;
    }

    /**
     * Retorna o veículo associado ao histórico de utilização.
     * 
     * @return o veículo associado.
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Define o veículo associado ao histórico de utilização.
     * 
     * @param veiculo o veículo associado.
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Retorna uma representação em string do histórico de utilização.
     * 
     * @return representação em string do histórico de utilização.
     */
    @Override
    public String toString() {
        return "HistoricoUtilizacao [id=" + id + ", entryDate=" + entryDate + ", leaveDate=" + leaveDate + ", spot="
                + spot + ", veiculo=" + veiculo + "]";
    }

}
