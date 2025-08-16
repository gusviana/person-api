package com.person_api.person_api.service;

import com.person_api.person_api.entity.Person;
import com.person_api.person_api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAllByOrderByNameAsc();
    }
}
