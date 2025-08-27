package com.person_api.person_api.exception;

public class CpfAlreadyExistsException extends RuntimeException{

    public CpfAlreadyExistsException(String message){
        super("CPF já está em uso: " + message);
    }
}
