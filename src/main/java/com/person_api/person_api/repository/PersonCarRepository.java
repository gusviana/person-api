package com.person_api.person_api.repository;

import com.person_api.person_api.entity.PersonCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonCarRepository extends JpaRepository<PersonCar, Long> {
    Optional<PersonCar> findByCpf(String cpf);

    List<PersonCar> findByPlaca(String placa);
}
