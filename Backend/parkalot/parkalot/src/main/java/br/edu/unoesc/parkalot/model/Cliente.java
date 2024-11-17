package br.edu.unoesc.parkalot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe que representa um Cliente no sistema. Um cliente pode possuir um ou
 * mais veículos e seus dados estão mapeados na tabela 'cliente' do banco de
 * dados.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false, unique = true)
    private Long id;

    /**
     * CPF do cliente, valor único e obrigatório.
     */
    @Column(name = "client_cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    /**
     * Nome completo do cliente.
     */
    @Column(name = "client_name", length = 40, nullable = false)
    private String name;

    /**
     * Telefone de contato do cliente.
     */
    @Column(name = "client_phone", length = 11, nullable = false)
    private String phone;

    /**
     * E-mail do cliente, valor único e obrigatório.
     */
    @Column(name = "client_mail", length = 30, nullable = false, unique = true)
    private String email;

    /**
     * Idade do cliente.
     */
    @Column(name = "client_age", nullable = false)
    private Integer age;

    /**
     * Sexo do cliente (M - Masculino, F - Feminino).
     */
    @Column(name = "client_sex", length = 1, nullable = false)
    private String sex;

    /**
     * Lista de veículos associados ao cliente.
     */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Veiculo> veiculos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", cpf=" + cpf + ", name=" + name + ", phone=" + phone + ", email=" + email
                + ", age=" + age + ", sex=" + sex + ", veiculos=" + veiculos + "]";
    }

}
