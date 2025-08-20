package com.person_api.person_api.repository;

import com.person_api.person_api.dto.PersonDto;
import com.person_api.person_api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByOrderByNameAsc();

    Optional<Person> findByCpf(String cpf);

    List<Person> findByNameStartingWithIgnoreCaseOrderByName(String prefix);

}
