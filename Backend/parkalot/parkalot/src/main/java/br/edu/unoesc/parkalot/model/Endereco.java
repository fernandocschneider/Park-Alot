package br.edu.unoesc.parkalot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Classe que representa o Endereço de um Cliente no sistema.
 * Está mapeada na tabela 'endereco' do banco de dados e possui uma relação de
 * um-para-um com a entidade Cliente.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "endereco")
public class Endereco {

    /**
     * Identificador único do endereço.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_number", nullable = false, unique = true)
    private Long id;

    /**
     * Nome da rua do endereço.
     */
    @Column(name = "adress_street", length = 30, nullable = false)
    private String street;

    /**
     * Bairro do endereço.
     */
    @Column(name = "adress_neighbor", length = 30, nullable = false)
    private String neighbor;

    /**
     * Complemento do endereço (opcional).
     */
    @Column(name = "adress_extra", length = 30)
    private String extra;

    /**
     * Cidade onde o endereço está localizado.
     */
    @Column(name = "adress_city", length = 40, nullable = false)
    private String city;

    /**
     * Cliente associado ao endereço.
     * Relação de um-para-um entre Endereço e Cliente.
     */
    @OneToOne
    @JoinColumn(name = "client_cpf", referencedColumnName = "client_cpf", nullable = false)
    private Cliente client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Endereco [id=" + id + ", street=" + street + ", neighbor=" + neighbor + ", extra=" + extra + ", city="
                + city + ", client=" + client + "]";
    }

}
