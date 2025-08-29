package com.person_api.person_api.repository;

import com.person_api.person_api.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
