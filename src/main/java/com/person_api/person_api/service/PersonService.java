package com.person_api.person_api.service;

import com.person_api.person_api.entity.Person;
import com.person_api.person_api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAllByOrderByNameAsc();
    }

    public Person findById(Long id){
        Optional<Person> person = personRepository.findById(id);
        return person.get();
    }

    public Person insert(Person person){
        return personRepository.save(person);
    }
}
