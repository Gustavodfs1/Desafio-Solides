package com.solides.desafio.exception;

import lombok.Getter;

@Getter
public class AlbumNotFoundException extends RuntimeException {

    private final Integer albumId;


    public AlbumNotFoundException(Integer albumId) {
        super("Album not found.");
        this.albumId = albumId;
    }
}
