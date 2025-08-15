package com.person_api.person_api.entity;

import com.person_api.person_api.entity.enums.GenderEnum;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo nome não pode ser nulo!")
    private String name;

    private LocalDate dataNascimento;

    @CPF(message = "CPF inválido")
    @NotNull
    private String cpf;

    private GenderEnum genderEnum;

    public Person() {
    }

    public Person(Long id, GenderEnum genderEnum, String cpf, LocalDate dataNascimento, String name) {
        this.id = id;
        this.genderEnum = genderEnum;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @NotNull String getCpf() {
        return cpf;
    }

    public void setCpf( String cpf) {
        this.cpf = cpf;
    }

    public GenderEnum getGender() {
        return genderEnum;
    }

    public void setGender(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public Integer getIdade() {
        if (this.dataNascimento == null) {
            return null;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
