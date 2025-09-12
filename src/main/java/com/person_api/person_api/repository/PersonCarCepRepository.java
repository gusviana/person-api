package com.person_api.person_api.repository;

import com.person_api.person_api.entity.PersonCarCep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonCarCepRepository extends JpaRepository<PersonCarCep, Long> {
    Optional<PersonCarCep> findByCpf(String cpf);
}
