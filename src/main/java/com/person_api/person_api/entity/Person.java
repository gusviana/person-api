package com.person_api.person_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.person_api.person_api.entity.enums.GenderEnumConverter;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.person_api.person_api.entity.enums.GenderEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "tb_person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "O campo nome não pode ser nulo!")
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @CPF(message = "CPF inválido")
    @NotNull
    @Column(unique = true)
    private String cpf;

    @Convert(converter = GenderEnumConverter.class)
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum genderEnum;

    public Integer getIdade() {
        if (this.dataNascimento == null) {
            return null;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}
