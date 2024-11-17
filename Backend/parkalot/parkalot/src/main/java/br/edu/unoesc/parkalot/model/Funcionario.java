package br.edu.unoesc.parkalot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe que representa um funcionário no sistema.
 * Contém informações sobre o CPF, nome, telefone, email, cargo, salário e data
 * de admissão do funcionário.
 * 
 * @author Jean Toral
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "worker_cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "worker_name", length = 40, nullable = false)
    private String name;

    @Column(name = "worker_phone", length = 11, nullable = false)
    private String phone;

    @Column(name = "worker_mail", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "worker_role", length = 30, nullable = false)
    private String role;

    @Column(name = "worker_salary", nullable = false)
    private Double salary;

    @Column(name = "worker_admission", nullable = false)
    private Integer admission;

    /**
     * Retorna o ID do funcionário.
     * 
     * @return o ID do funcionário.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do funcionário.
     * 
     * @param id o ID do funcionário.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o CPF do funcionário.
     * 
     * @return o CPF do funcionário.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do funcionário.
     * 
     * @param cpf o CPF do funcionário.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o nome do funcionário.
     * 
     * @return o nome do funcionário.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do funcionário.
     * 
     * @param name o nome do funcionário.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o telefone do funcionário.
     * 
     * @return o telefone do funcionário.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Define o telefone do funcionário.
     * 
     * @param phone o telefone do funcionário.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retorna o e-mail do funcionário.
     * 
     * @return o e-mail do funcionário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do funcionário.
     * 
     * @param email o e-mail do funcionário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o cargo do funcionário.
     * 
     * @return o cargo do funcionário.
     */
    public String getRole() {
        return role;
    }

    /**
     * Define o cargo do funcionário.
     * 
     * @param role o cargo do funcionário.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Retorna o salário do funcionário.
     * 
     * @return o salário do funcionário.
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Define o salário do funcionário.
     * 
     * @param salary o salário do funcionário.
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    /**
     * Retorna o ano de admissão do funcionário.
     * 
     * @return o ano de admissão do funcionário.
     */
    public Integer getAdmission() {
        return admission;
    }

    /**
     * Define o ano de admissão do funcionário.
     * 
     * @param admission o ano de admissão do funcionário.
     */
    public void setAdmission(Integer admission) {
        this.admission = admission;
    }

    /**
     * Retorna uma representação em string do funcionário.
     * 
     * @return representação em string do funcionário.
     */
    @Override
    public String toString() {
        return "Funcionario [id=" + id + ", cpf=" + cpf + ", name=" + name + ", phone=" + phone + ", email=" + email
                + ", role=" + role + ", salary=" + salary + ", admission=" + admission + "]";
    }

}
