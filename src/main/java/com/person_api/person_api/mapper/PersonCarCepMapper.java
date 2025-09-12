package com.person_api.person_api.mapper;

import com.person_api.person_api.dto.PersonCarCepDto;
import com.person_api.person_api.dto.PersonCarDto;
import com.person_api.person_api.entity.PersonCar;
import com.person_api.person_api.entity.PersonCarCep;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class PersonCarCepMapper {

    public PersonCarCepDto toDto(PersonCarCep entity) {
        return PersonCarCepDto.builder()
                .cpf(entity.getCpf())
                .placa(entity.getPlaca())
                .cep(entity.getCep())
                .build();
    }

    public PersonCarCep toEntity(PersonCarCepDto dto) {
        return PersonCarCep.builder()
                .cpf(dto.getCpf())
                .placa(dto.getPlaca())
                .cep(dto.getCep())
                .build();
    }

    public List<PersonCarCepDto> toDtoList(List<PersonCarCep> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<PersonCarCep> toEntityList(List<PersonCarCepDto> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
