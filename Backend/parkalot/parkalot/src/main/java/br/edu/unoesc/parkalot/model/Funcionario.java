package br.edu.unoesc.parkalot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Long id;

    @NotNull
    @Column(name = "worker_cpf", length = 11, nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "worker_name", length = 40, nullable = false)
    private String name;

    @NotNull
    @Column(name = "worker_phone", nullable = false)
    private Integer phone;

    @NotNull
    @Column(name = "worker_mail", length = 30, nullable = false)
    private String email;

    @NotNull
    @Column(name = "worker_role", length = 30, nullable = false)
    private String role;

    @NotNull
    @Column(name = "worker_salary", nullable = false)
    private Integer salary;

    @NotNull
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
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
        return "Cliente{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", salary='" + salary + '\'' +
                ", admission='" + admission + '\'' +
                '}';
    }

}