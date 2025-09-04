package com.person_api.person_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tb_person_car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class PersonCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo cpf não pode ser nulo!")
    private String cpf;

    @NotNull(message = "O campo placa não pode ser nulo!")
    private String placa;

    @NotNull(message = "O campo nome não pode ser nulo!")
    private String name;
}
