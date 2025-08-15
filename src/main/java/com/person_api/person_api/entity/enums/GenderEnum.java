package com.person_api.person_api.entity.enums;

public enum GenderEnum {
    MALE(1),
    FEMALE(2);

    private int code;

    private GenderEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static GenderEnum valueOf(int code) {
        for (GenderEnum value : GenderEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}