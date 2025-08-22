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

    public List<PersonDto> findAll() {
        return personRepository.findAllByOrderByNameAsc()
                .stream().map(personMapper::toDto).toList();
    }

    public PersonDto findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        return personMapper.toDto(person);
    }

    public PersonDto findByCpf(String cpf) {
        Person person = personRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com CPF: " + cpf));
        return personMapper.toDto(person);
    }

    public List<PersonDto> findByPrefix(String prefix) {
        if (prefix == null || prefix.length() < 3) {
            return Collections.emptyList();
        }
        return personRepository.findByNameStartingWithIgnoreCaseOrderByName(prefix)
                .stream().map(personMapper::toDto).toList();
    }

    public PersonDto insert(PersonDto dto) {
        Person entity = personMapper.toEntity(dto);
        entity = personRepository.save(entity);
        return personMapper.toDto(entity);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public PersonDto update(Long id, PersonDto dto) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        updateData(entity, dto);
        Person updated = personRepository.save(entity);
        return personMapper.toDto(updated);
    }

    private void updateData(Person entity, PersonDto dto) {
        entity.setName(dto.getName());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setCpf(dto.getCpf());
        entity.setGenderEnum(dto.getGenderEnum());
    }
}
