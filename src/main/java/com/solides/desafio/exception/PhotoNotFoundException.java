package com.solides.desafio.exception;

import lombok.Getter;

@Getter
public class PhotoNotFoundException extends RuntimeException {

    private final Integer photoId;


    public PhotoNotFoundException(Integer photoId) {
        super("Photo not found.");
        this.photoId = photoId;
    }
}
