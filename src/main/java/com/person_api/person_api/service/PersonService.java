package com.person_api.person_api.service;

import com.person_api.person_api.dto.PersonDto;
import com.person_api.person_api.entity.Person;
import com.person_api.person_api.mapper.PersonMapper;
import com.person_api.person_api.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<Person> findAll() {
        personRepository.findAll().stream().map(it -> personMapper.toDtoList(it)).collect(Collectors.toList());


        return personRepository.findAllByOrderByNameAsc();
    }

    public Person findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.get();
    }

    public Person findByCpf(String cpf){
        return personRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com CPF: " + cpf));
    }

    public List<Person> findByPrefix(String prefix){
        if(prefix == null || prefix.length() < 3){
            return Collections.emptyList();
        }
        return personRepository.findByNameStartingWithIgnoreCaseOrderByName(prefix);
    }

    public Person insert(Person person) {
        return personRepository.save(person);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Person update(Long id, Person person) {
        Person entity = personRepository.getReferenceById(id);
        updateData(entity, person);
        return personRepository.save(entity);
    }

    private void updateData(Person entity, Person person) {
        entity.setName(person.getName());
        entity.setDataNascimento(person.getDataNascimento());
    }
}
