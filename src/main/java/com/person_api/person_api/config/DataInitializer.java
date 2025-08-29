package com.person_api.person_api.config;

import com.person_api.person_api.entity.Car;
import com.person_api.person_api.repository.CarRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final CarRepository carRepository;

    @PostConstruct
    public void init() {
        // Se a coleção estiver vazia, insere alguns carros
        if (carRepository.count() == 0) {
            carRepository.save(Car.builder()
                    .modelo("Gol")
                    .fabricante("Volkswagen")
                    .placa("ABC1234")
                    .ano(2020)
                    .cor("Prata")
                    .build());

            carRepository.save(Car.builder()
                    .modelo("Civic")
                    .fabricante("Honda")
                    .placa("DEF5678")
                    .ano(2021)
                    .cor("Preto")
                    .build());

            carRepository.save(Car.builder()
                    .modelo("Corolla")
                    .fabricante("Toyota")
                    .placa("GHI9012")
                    .ano(2022)
                    .cor("Branco")
                    .build());
        }
    }
}