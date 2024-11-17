package br.edu.unoesc.parkalot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe que representa um veículo no sistema.
 * A tabela 'veiculo' contém informações sobre a placa, marca, modelo,
 * cor, ano de fabricação e o proprietário do veículo.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veiculo_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "veicule_sign", length = 8, nullable = false, unique = true)
    private String plate;

    /**
     * Marca do veículo.
     */
    @Column(name = "veicule_brand", length = 16, nullable = false)
    private String brand;

    /**
     * Modelo do veículo.
     */
    @Column(name = "veicule_model", length = 16, nullable = false)
    private String model;

    /**
     * Cor do veículo.
     */
    @Column(name = "veicule_color", length = 16)
    private String color;

    /**
     * Ano de fabricação do veículo.
     */
    @Column(name = "veicule_year", length = 4)
    private String year;

    /**
     * Proprietário do veículo, que é uma referência à entidade Cliente.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
    @JsonIgnoreProperties({ "id", "name", "phone", "email", "age", "sex", "veiculos" })
    private Cliente owner;

    @OneToMany(mappedBy = "veiculo")
    private List<Reserva> reservas; // Relacionamento com a tabela Reserva

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
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
        return "Veiculo [id=" + id + ", plate=" + plate + ", brand=" + brand + ", model=" + model + ", color=" + color
                + ", year=" + year + ", owner=" + owner + "]";
    }

}
