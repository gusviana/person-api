package com.person_api.person_api.client.dto.endereco;


import lombok.*;

@Data
public class Endereco {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
