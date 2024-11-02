package br.edu.unoesc.parkalot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @NotNull
    @Column(name = "client_cpf", length = 11, nullable = false)
    @Pattern(regexp = "\\d{11}", message = "O CPF precisa ter mais de 11 dígitos")
    private String cpf;

    @NotNull
    @Column(name = "client_name", length = 40, nullable = false)
    @Size(min = 2, max = 40, message = "O nome precisa ter entre 2 e 40 caracteres")
    private String name;

    @NotNull
    @Column(name = "client_phone", nullable = false)
    private Integer phone;

    @NotNull
    @Column(name = "client_mail", length = 30, nullable = false)
    @Email(message = "Por favor, informe um endereço de e-mail válido")
    private String email;

    @NotNull
    @Column(name = "client_age", nullable = false)
    @Min(value = 0, message = "idade não pode ser negativa")
    private Integer age;

    @Column(name = "client_sex", length = 1)
    @Pattern(regexp = "[MF]", message = "Sex must be either 'M' or 'F'")
    private String sex;

    public Cliente() {
    }

    // Getters e Setters
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

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}