package com.person_api.person_api.mapper;

import com.person_api.person_api.dto.PersonCarDto;
import com.person_api.person_api.entity.PersonCar;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class PersonCarMapper {

    public PersonCarDto toDto(PersonCar entity) {
        PersonCarDto dto = new PersonCarDto();
        dto.setCpf(entity.getCpf());
        dto.setPlaca(entity.getPlaca());
        dto.setName(entity.getName());
        return dto;
    }

    public PersonCar toEntity(PersonCarDto dto) {
        PersonCar entity = new PersonCar();
        entity.setCpf(dto.getCpf());
        entity.setPlaca(dto.getPlaca());
        entity.setName(dto.getName());
        return entity;
    }

    public List<PersonCarDto> toDtoList(List<PersonCar> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<PersonCar> toEntityList(List<PersonCarDto> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
