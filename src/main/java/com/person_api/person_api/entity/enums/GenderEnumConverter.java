package com.person_api.person_api.entity.enums;

import jakarta.persistence.AttributeConverter;

public class GenderEnumConverter implements AttributeConverter<GenderEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GenderEnum attribute) {
        if (attribute == null) return null;
        return attribute.getCode();
    }

    @Override
    public GenderEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        return GenderEnum.valueOf(dbData);
    }

}
