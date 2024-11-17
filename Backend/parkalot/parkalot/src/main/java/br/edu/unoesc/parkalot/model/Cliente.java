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
 * Classe que representa um cliente no sistema. Contém informações pessoais do
 * cliente,
 * incluindo dados de contato e relacionamento com veículos e reservas.
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

    @Column(name = "client_cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "client_name", length = 40, nullable = false)
    private String name;

    @Column(name = "client_phone", length = 11, nullable = false)
    private String phone;

    @Column(name = "client_mail", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "client_age", nullable = false)
    private Integer age;

    @Column(name = "client_sex", length = 1, nullable = false)
    private String sex;

    /**
     * Relacionamento um-para-muitos com a entidade Reserva. Um cliente pode ter
     * várias reservas.
     */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    /**
     * Relacionamento um-para-muitos com a entidade Veiculo. Um cliente pode ter
     * vários veículos.
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Veiculo> veiculos;

    /**
     * Retorna o ID do cliente.
     * 
     * @return ID do cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do cliente.
     * 
     * @param id o ID do cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o CPF do cliente.
     * 
     * @return CPF do cliente.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     * 
     * @param cpf o CPF do cliente.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o nome do cliente.
     * 
     * @return nome do cliente.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do cliente.
     * 
     * @param name o nome do cliente.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o telefone do cliente.
     * 
     * @return telefone do cliente.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Define o telefone do cliente.
     * 
     * @param phone o telefone do cliente.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retorna o email do cliente.
     * 
     * @return email do cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do cliente.
     * 
     * @param email o email do cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a idade do cliente.
     * 
     * @return idade do cliente.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Define a idade do cliente.
     * 
     * @param age a idade do cliente.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Retorna o sexo do cliente.
     * 
     * @return sexo do cliente.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Define o sexo do cliente.
     * 
     * @param sex o sexo do cliente.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Retorna a lista de veículos do cliente.
     * 
     * @return lista de veículos do cliente.
     */
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    /**
     * Define a lista de veículos do cliente.
     * 
     * @param veiculos a lista de veículos do cliente.
     */
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    /**
     * Retorna uma representação em string do cliente.
     * 
     * @return representação em string do cliente.
     */
    @Override
    public String toString() {
        return "Cliente [id=" + id + ", cpf=" + cpf + ", name=" + name + ", phone=" + phone + ", email=" + email
                + ", age=" + age + ", sex=" + sex + ", veiculos=" + veiculos + "]";
    }

}
