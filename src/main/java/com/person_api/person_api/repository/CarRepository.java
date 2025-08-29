package com.person_api.person_api.repository;

import com.person_api.person_api.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String> {

}
