package com.person_api.person_api.service;

import com.person_api.person_api.dto.PersonDto;
import com.person_api.person_api.entity.Car;
import com.person_api.person_api.entity.Person;
import com.person_api.person_api.exception.ResourceNotFoundException;
import com.person_api.person_api.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Car findById(Long id){
        Optional<Car> car = carRepository.findById(id);
        return car.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Car insert(Car car){
        return carRepository.save(car);
    }

    public void delete(Long id){
        if(!carRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        carRepository.deleteById(id);
    }

    public Car update(Long id, Car car){
        Car entity = carRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));

        updateData(entity, car);
        return carRepository.save(entity);
    }

    private void updateData(Car entity, Car car) {
        entity.setModelo(car.getModelo());
        entity.setPlaca(car.getPlaca());
        entity.setCor(car.getCor());
    }
}
