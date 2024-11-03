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
@Table(name = "Veiculo")

public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veicule_sign", length = 8, nullable = false, unique = true)
    private String id;

    @NotNull
    @Column(name = "veicule_brand", length = 16, nullable = false)
    private String brand;

    @NotNull
    @Column(name = "veicule_model", length = 16, nullable = false)
    private String model;

    @NotNull
    @Column(name = "veicule_color", length = 16, nullable = false)
    private String color;

    @NotNull
    @Column(name = "veicule_year", length = 4, nullable = false)
    private String year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_cpf", referencedColumnName = "client_cpf", nullable = false)
    private Cliente owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Cliente getOwner() {
        return owner;
    }

    public void setOwner(Cliente owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year='" + year + '\'' +
                ", owner=" + owner +
                '}';
    }

    
}
