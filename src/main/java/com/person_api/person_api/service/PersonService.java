package com.person_api.person_api.service;

import com.person_api.person_api.dto.PersonDto;
import com.person_api.person_api.entity.Person;
import com.person_api.person_api.exception.CpfAlreadyExistsException;
import com.person_api.person_api.exception.InvalidPrefixException;
import com.person_api.person_api.exception.ResourceNotFoundException;
import com.person_api.person_api.mapper.PersonMapper;
import com.person_api.person_api.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<PersonDto> findAll() {
        log.info("Inicio de lista com todas pessoas");
        List<PersonDto> persons = personRepository.findAllByOrderByNameAsc()
                .stream().map(personMapper::toDto).toList();
        log.info("Total de pessoas encontradas: {}", persons.size());
        return persons;
    }

    public PersonDto findById(Long id) {
        log.info("Inicio de busca da pessoa ID: {}", id);
        Person person = personRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Pessoa não encontrada para ID: {}", id);
                    return new ResourceNotFoundException(id);
                });
        log.info("Pessoa encontrada com o ID: {}", id);
        return personMapper.toDto(person);
    }

    public PersonDto findByCpf(String cpf) {
        log.info("Inicio de busca da pessoa CPF: {}", cpf);
        Person person = personRepository.findByCpf(cpf)
                .orElseThrow(() -> {
                    log.warn("Pessoa não encontrada para CPF: {}", cpf);
                    return new ResourceNotFoundException(cpf);
                });
        log.info("Pessoa encontrada com o CPF: {}", cpf);
        return personMapper.toDto(person);
    }

    public List<PersonDto> findByPrefix(String prefix) {
        log.info("Inicio de busca das pessoas co prefix : {}", prefix);
        if (prefix == null || prefix.length() < 3) {
            log.warn("prefixo nao encontrad: {}", prefix);
            throw new InvalidPrefixException(prefix);
        }
        log.info("Pessoas encontradas com prefixo: {}", prefix);
        List<PersonDto> persons = personRepository.findByNameStartingWithIgnoreCaseOrderByName(prefix)
                .stream().map(personMapper::toDto).toList();
        log.info("Pessoas encontradas para prefixo '{}': {}", prefix, persons.size());
        return persons;
    }

    public PersonDto insert(PersonDto dto) {
        log.info("Inicio do cadastro de nova pessoa");
        if (personRepository.findByCpf(dto.getCpf()).isPresent()) {
            log.error("CPF já existente: {}", dto.getCpf());
            throw new CpfAlreadyExistsException(dto.getCpf());
        }

        Person entity = personMapper.toEntity(dto);
        entity = personRepository.save(entity);
        log.info("Pessoa adicionada com o ID: {}", entity.getId());
        return personMapper.toDto(entity);
    }

    public void delete(Long id) {
        log.info("Inicio do deleçao da pessoa ID: {}", id);
        if(!personRepository.existsById(id)){
            log.warn("Pessoa não encontrada para ID: {}", id);
            throw new ResourceNotFoundException(id);
        }
        log.info("Pessoa deletada com o ID: {}", id);
        personRepository.deleteById(id);
    }

    public PersonDto update(Long id, PersonDto dto) {
        log.info("Atualizando pessoa com ID: {}", id);
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Pessoa não encontrada para atualização. ID: {}", id);
                    return new ResourceNotFoundException(id)
                });

        Optional<Person> existingCpf = personRepository.findByCpf(dto.getCpf());
        if(existingCpf.isPresent() && !existingCpf.get().getId().equals(id)) {
            log.error("CPF duplicado detectado durante atualização: {}", dto.getCpf());
            throw new CpfAlreadyExistsException(dto.getCpf());
        }

        updateData(entity, dto);
        Person updated = personRepository.save(entity);
        log.info("Pessoa atualizada com sucesso: ID {}", updated.getId());
        return personMapper.toDto(updated);
    }

    private void updateData(Person entity, PersonDto dto) {
        log.info("Dados passiveis de atualizaçao");
        entity.setName(dto.getName());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setCpf(dto.getCpf());
        entity.setGenderEnum(dto.getGenderEnum());
    }
}
