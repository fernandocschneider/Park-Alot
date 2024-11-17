package br.edu.unoesc.parkalot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAdmission() {
        return admission;
    }

    public void setAdmission(Integer admission) {
        this.admission = admission;
    }

    @Override
    public String toString() {
        return "Funcionario [id=" + id + ", cpf=" + cpf + ", name=" + name + ", phone=" + phone + ", email=" + email
                + ", role=" + role + ", salary=" + salary + ", admission=" + admission + "]";
    }

}
