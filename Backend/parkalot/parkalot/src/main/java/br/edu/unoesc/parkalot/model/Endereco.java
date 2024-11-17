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
 * Classe que representa o endereço de um cliente no sistema.
 * Contém informações de localização, como rua, bairro, cidade e informações
 * sobre o cliente associado a este endereço.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_number", nullable = false, unique = true)
    private Long id;

    @Column(name = "adress_street", length = 30, nullable = false)
    private String street;

    @Column(name = "adress_neighbor", length = 30, nullable = false)
    private String neighbor;

    @Column(name = "adress_extra", length = 30)
    private String extra;

    @Column(name = "adress_city", length = 40, nullable = false)
    private String city;

    /**
     * Relacionamento um-para-um com a entidade Cliente.
     * Cada endereço está associado a um cliente único.
     */
    @OneToOne
    @JoinColumn(name = "client_cpf", referencedColumnName = "client_cpf", nullable = false)
    private Cliente client;

    /**
     * Retorna o ID do endereço.
     * 
     * @return o ID do endereço.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do endereço.
     * 
     * @param id o ID do endereço.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome da rua do endereço.
     * 
     * @return o nome da rua do endereço.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Define o nome da rua do endereço.
     * 
     * @param street o nome da rua do endereço.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Retorna o nome do bairro do endereço.
     * 
     * @return o nome do bairro do endereço.
     */
    public String getNeighbor() {
        return neighbor;
    }

    /**
     * Define o nome do bairro do endereço.
     * 
     * @param neighbor o nome do bairro do endereço.
     */
    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
    }

    /**
     * Retorna informações extras do endereço, como complemento, se houver.
     * 
     * @return informações extras do endereço.
     */
    public String getExtra() {
        return extra;
    }

    /**
     * Define informações extras do endereço, como complemento, se houver.
     * 
     * @param extra informações extras do endereço.
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * Retorna o nome da cidade do endereço.
     * 
     * @return o nome da cidade do endereço.
     */
    public String getCity() {
        return city;
    }

    /**
     * Define o nome da cidade do endereço.
     * 
     * @param city o nome da cidade do endereço.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retorna o cliente associado a este endereço.
     * 
     * @return o cliente associado ao endereço.
     */
    public Cliente getClient() {
        return client;
    }

    /**
     * Define o cliente associado a este endereço.
     * 
     * @param client o cliente associado ao endereço.
     */
    public void setClient(Cliente client) {
        this.client = client;
    }

    /**
     * Retorna uma representação em string do endereço.
     * 
     * @return representação em string do endereço.
     */
    @Override
    public String toString() {
        return "Endereco [id=" + id + ", street=" + street + ", neighbor=" + neighbor + ", extra=" + extra + ", city="
                + city + ", client=" + client + "]";
    }

}
