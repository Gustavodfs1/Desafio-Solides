package com.solides.desafio.exception;

import lombok.Getter;

@Getter
public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException() {
        super("You are not authorized to do this action.");
    }
}
