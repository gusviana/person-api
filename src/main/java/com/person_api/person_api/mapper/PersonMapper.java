package com.person_api.person_api.mapper;

import com.person_api.person_api.dto.PersonDto;
import com.person_api.person_api.entity.Person;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class PersonMapper {

    public PersonDto toDto(Person person) {
        if (person == null) return null;
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .dataNascimento(person.getDataNascimento())
                .cpf(person.getCpf())
                .genderEnum(person.getGenderEnum())
                .build();
    }

    public Person toEntity(PersonDto dto) {
        if (dto == null) return null;

        return Person.builder()
                .id(dto.getId())
                .name(dto.getName())
                .dataNascimento(dto.getDataNascimento())
                .cpf(dto.getCpf())
                .genderEnum(dto.getGenderEnum())
                .build();
    }

    public List<PersonDto> toDtoList(List<Person> people) {
        return people.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Person> toEntityList(List<PersonDto> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
