package com.person_api.person_api.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Resource not found! ID: " + id);
    }

    public ResourceNotFoundException(String msg){
        super("Pessoa não encontrada com CPF: " + msg);
    }
}
