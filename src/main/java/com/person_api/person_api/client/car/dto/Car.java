package com.person_api.person_api.client.car.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {
    private String id;
    private String placa;
    private String modelo;
    private String renavam;
}
