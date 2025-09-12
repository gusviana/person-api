package com.person_api.person_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_person_car_cpf")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class PersonCarCep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo CPF não pode ser nulo!")
    private String cpf;

    @NotNull(message = "O campo placa não pode ser nulo!")
    private String placa;

    @NotNull(message = "O campo cep não pode ser nulo!")
    private String cep;
}
