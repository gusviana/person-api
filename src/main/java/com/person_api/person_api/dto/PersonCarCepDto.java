package com.person_api.person_api.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class PersonCarCepDto {
    private long id;
    private String cpf;
    private String placa;
    private String cep;
}
