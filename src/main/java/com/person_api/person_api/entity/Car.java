package com.person_api.person_api.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "tb_car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Car implements Serializable {

    @Id
    private String id;

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
