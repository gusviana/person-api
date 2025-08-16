package com.person_api.person_api.repository;

import com.person_api.person_api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByOrderByNameAsc();
}
