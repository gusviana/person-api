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

    public void delete(Long id){
        personRepository.deleteById(id);
    }

    public Person update(Long id, Person person){
        Person entity = personRepository.getReferenceById(id);
        updateData(entity, person);
        return personRepository.save(entity);
    }

    private void updateData(Person entity, Person person){
        entity.setName(person.getName());
        entity.setDataNascimento(person.getDataNascimento());
    }
}
