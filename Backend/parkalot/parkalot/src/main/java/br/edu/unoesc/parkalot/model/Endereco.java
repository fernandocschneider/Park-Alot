package br.edu.unoesc.parkalot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Endereço")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_number", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "adress_street", length = 30, nullable = false)
    private String street;

    @NotNull
    @Column(name = "adress_neighbor", length = 30, nullable = false)
    private String neighbor;

    @NotNull
    @Column(name = "adress_extra", length = 30, nullable = false)
    private String extra;

    @NotNull
    @Column(name = "adress_city", length = 40, nullable = false)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_cpf", referencedColumnName = "client_cpf", nullable = false)
    private Cliente owner;

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

    public Cliente getOwner() {
        return owner;
    }

    public void setOwner(Cliente owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", neighbor='" + neighbor + '\'' +
                ", extra='" + extra + '\'' +
                ", city='" + city + '\'' +
                ", owner=" + owner +
                '}';
    }

}
