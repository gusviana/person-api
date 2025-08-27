package com.person_api.person_api.exception;

public class InvalidPrefixException extends RuntimeException{

    public InvalidPrefixException(String prefix){

        super("Prefixo inválido: '" + prefix + "'. Deve conter pelo menos 3 caracteres.");
    }
}
