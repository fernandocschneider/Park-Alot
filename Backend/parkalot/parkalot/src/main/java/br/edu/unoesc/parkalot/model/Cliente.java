package br.edu.unoesc.parkalot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "client_cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @NotNull
    @Column(name = "client_name", length = 40, nullable = false)
    private String name;

    @NotNull
    @Column(name = "client_phone", length = 11,nullable = false)
    private String phone;

    @NotNull
    @Column(name = "client_mail", length = 30, nullable = false)
    private String email;

    @NotNull
    @Column(name = "client_age", nullable = false)
    private Byte age;

    @Column(name = "client_sex", length = 1, nullable = false)
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

    public @NotNull Byte getAge() {
        return age;
    }

    public void setAge(@NotNull Byte age) {
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