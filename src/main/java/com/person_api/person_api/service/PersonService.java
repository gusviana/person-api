package com.person_api.person_api.service;

import com.person_api.person_api.client.CarClient;
import com.person_api.person_api.client.car.dto.Car;
import com.person_api.person_api.dto.PersonDto;
import com.person_api.person_api.entity.Person;
import com.person_api.person_api.entity.PersonCar;
import com.person_api.person_api.exception.CpfAlreadyExistsException;
import com.person_api.person_api.exception.InvalidPrefixException;
import com.person_api.person_api.exception.ResourceNotFoundException;
import com.person_api.person_api.mapper.PersonMapper;
import com.person_api.person_api.repository.PersonCarRepository;
import com.person_api.person_api.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonCarRepository personCarRepository;
    private final PersonMapper personMapper;
    private final MessageSource messageSource;
    private final CarClient carClient;

    private String getMessage(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }

    public List<PersonDto> findAll() {
        log.info(getMessage("log.person.findAll.start"));
        List<PersonDto> persons = personRepository.findAllByOrderByNameAsc()
                .stream().map(personMapper::toDto).toList();
        log.info(getMessage("log.person.findAll.result", persons.size()));
        return persons;
    }

    public PersonDto findById(Long id) {
        log.info(getMessage("log.person.findById.start", id));
        Person person = personRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(getMessage("log.person.findById.notFound", id));
                    return new ResourceNotFoundException(id);
                });
        log.info(getMessage("log.person.findById.success", id));
        return personMapper.toDto(person);
    }

    public PersonDto findByCpf(String cpf) {
        log.info(getMessage("log.person.findByCpf.start", cpf));
        Person person = personRepository.findByCpf(cpf)
                .orElseThrow(() -> {
                    log.warn(getMessage("log.person.findByCpf.notFound", cpf));
                    return new ResourceNotFoundException(cpf);
                });
        log.info(getMessage("log.person.findByCpf.success", cpf));
        return personMapper.toDto(person);
    }

    public List<PersonDto> findByPrefix(String prefix) {
        log.info(getMessage("log.person.findByPrefix.start", prefix));
        if (prefix == null || prefix.length() < 3) {
            log.warn(getMessage("log.person.findByPrefix.invalid", prefix));
            throw new InvalidPrefixException(prefix);
        }
        log.info(getMessage("log.person.findByPrefix.success", prefix));
        List<PersonDto> persons = personRepository.findByNameStartingWithIgnoreCaseOrderByName(prefix)
                .stream().map(personMapper::toDto).toList();
        log.info(getMessage("log.person.findByPrefix.count", prefix, persons.size()));
        return persons;
    }

    public PersonDto insert(PersonDto dto) {
        log.info(getMessage("log.person.insert.start"));
        if (personRepository.findByCpf(dto.getCpf()).isPresent()) {
            log.error(getMessage("log.person.insert.cpfExists", dto.getCpf()));
            throw new CpfAlreadyExistsException(dto.getCpf());
        }

        Person entity = personMapper.toEntity(dto);
        entity = personRepository.save(entity);
        log.info(getMessage("log.person.insert.success", entity.getId()));
        return personMapper.toDto(entity);
    }

    public void delete(Long id) {
        log.info(getMessage("log.person.delete.start", id));
        if(!personRepository.existsById(id)){
            log.warn(getMessage("log.person.delete.notFound", id));
            throw new ResourceNotFoundException(id);
        }
        log.info(getMessage("log.person.delete.success", id));
        personRepository.deleteById(id);
    }

    public PersonDto update(Long id, PersonDto dto) {
        log.info(getMessage("log.person.update.start", id));
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(getMessage("log.person.update.notFound", id));
                    return new ResourceNotFoundException(id);
                });

        Optional<Person> existingCpf = personRepository.findByCpf(dto.getCpf());
        if(existingCpf.isPresent() && !existingCpf.get().getId().equals(id)) {
            log.error(getMessage("log.person.update.cpfExists", dto.getCpf()));
            throw new CpfAlreadyExistsException(dto.getCpf());
        }

        updateData(entity, dto);
        Person updated = personRepository.save(entity);
        log.info(getMessage("log.person.update.success", updated.getId()));
        return personMapper.toDto(updated);
    }

    private void updateData(Person entity, PersonDto dto) {
        log.info(getMessage("log.person.updateData.start"));
        entity.setName(dto.getName());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setCpf(dto.getCpf());
        entity.setGenderEnum(dto.getGenderEnum());
    }

    public PersonCar linkPersonToCar(String cpf, String placa){
        personCarRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com CPF: " + cpf));

        Car car = carClient.getCarByPlaca(placa);
        if(car == null){
            throw new ResourceNotFoundException("Carro não encontrado com a placa: " + placa);
        }

        PersonCar personCar = new PersonCar();
        personCar.setCpf(cpf);
        personCar.setPlaca(placa);

        return personCarRepository.save(personCar);
    }

    public PersonCar getCarByPerson(String cpf) {
        return personCarRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum carro vinculado para o CPF: " + cpf));
    }
}
