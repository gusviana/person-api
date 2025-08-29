package com.person_api.person_api.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_Car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo modelo não pode ser nulo!")
    private String modelo;

    @NotNull(message = "O campo fabricante não pode ser nulo!")
    private String fabricante;

    @NotNull(message = "O campo placa não pode ser nulo!")
    private String placa;


    @NotNull(message = "O campo ano não pode ser nulo!")
    private Integer ano;

    private String cor;
}
