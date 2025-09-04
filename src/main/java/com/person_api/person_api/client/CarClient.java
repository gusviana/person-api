package com.person_api.person_api.client;

import com.person_api.person_api.client.car.dto.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "car-service", url = "${car.client.url}")
public interface CarClient {

    @GetMapping("/cars/person/{personId}")
    List<String> getCarsByPerson(@PathVariable("personId") Long personId);

    @GetMapping("/cars/{placa}")
    Optional<Car> getCarByPlaca(@PathVariable("placa") String placa);
}
 