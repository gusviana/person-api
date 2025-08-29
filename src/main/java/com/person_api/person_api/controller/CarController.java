package com.person_api.person_api.controller;

import com.person_api.person_api.entity.Car;
import com.person_api.person_api.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAll(){
        List<Car> list = carService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Car> findById(@PathVariable String id){
        Car car = carService.findById(id);
        return ResponseEntity.ok().body(car);
    }

    @PostMapping
    public ResponseEntity<Car> insert(@RequestBody Car car){
        car = carService.insert(car);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(car.getId()).toUri();
        return ResponseEntity.created(uri).body(car);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Car> update(@PathVariable String id, @RequestBody Car car){
        car = carService.update(id, car);
        return ResponseEntity.ok().body(car);
    }
}
