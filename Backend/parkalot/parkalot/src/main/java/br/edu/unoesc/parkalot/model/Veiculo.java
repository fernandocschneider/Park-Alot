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
 * Contém informações sobre a placa, marca, modelo, cor, ano de fabricação,
 * o proprietário do veículo e as reservas associadas ao veículo.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veicule_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "veicule_sign", length = 8, nullable = false, unique = true)
    private String plate;

    @Column(name = "veicule_brand", length = 16, nullable = false)
    private String brand;

    @Column(name = "veicule_model", length = 16, nullable = false)
    private String model;

    @Column(name = "veicule_color", length = 16)
    private String color;

    @Column(name = "veicule_year", length = 4)
    private String year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
    @JsonIgnoreProperties({ "id", "name", "phone", "email", "age", "sex", "veiculos" })
    private Cliente owner;

    @OneToMany(mappedBy = "veiculo")
    private List<Reserva> reservas;

    /**
     * Retorna o ID do veículo.
     * 
     * @return o ID do veículo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do veículo.
     * 
     * @param id o ID do veículo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna a placa do veículo.
     * 
     * @return a placa do veículo.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Define a placa do veículo.
     * 
     * @param plate a placa do veículo.
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * Retorna a marca do veículo.
     * 
     * @return a marca do veículo.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Define a marca do veículo.
     * 
     * @param brand a marca do veículo.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Retorna o modelo do veículo.
     * 
     * @return o modelo do veículo.
     */
    public String getModel() {
        return model;
    }

    /**
     * Define o modelo do veículo.
     * 
     * @param model o modelo do veículo.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Retorna a cor do veículo.
     * 
     * @return a cor do veículo.
     */
    public String getColor() {
        return color;
    }

    /**
     * Define a cor do veículo.
     * 
     * @param color a cor do veículo.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Retorna o ano de fabricação do veículo.
     * 
     * @return o ano de fabricação do veículo.
     */
    public String getYear() {
        return year;
    }

    /**
     * Define o ano de fabricação do veículo.
     * 
     * @param year o ano de fabricação do veículo.
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Retorna o proprietário do veículo.
     * 
     * @return o proprietário do veículo.
     */
    public Cliente getOwner() {
        return owner;
    }

    /**
     * Define o proprietário do veículo.
     * 
     * @param owner o proprietário do veículo.
     */
    public void setOwner(Cliente owner) {
        this.owner = owner;
    }

    /**
     * Retorna uma representação em string do objeto Veiculo.
     * 
     * @return representação em string do objeto Veiculo.
     */
    @Override
    public String toString() {
        return "Veiculo [id=" + id + ", plate=" + plate + ", brand=" + brand + ", model=" + model + ", color=" + color
                + ", year=" + year + ", owner=" + owner + "]";
    }
}
