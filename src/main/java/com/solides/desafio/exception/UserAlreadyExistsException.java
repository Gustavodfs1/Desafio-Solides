package com.solides.desafio.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class UserAlreadyExistsException extends RuntimeException{

    private final List<String> duplicatedFields;

    public UserAlreadyExistsException(String msg, List<String> duplicatedFields){
        super(msg);
        this.duplicatedFields = duplicatedFields;
    }
}
