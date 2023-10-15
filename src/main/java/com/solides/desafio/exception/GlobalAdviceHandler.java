package com.solides.desafio.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalAdviceHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorResponse userAlreadyExistsException(UserAlreadyExistsException e,
                                                    HttpServletRequest req) {
        return ErrorResponse.builder(e, HttpStatus.CONFLICT, e.getMessage())
                .title("Error registering new user")
                .property("Duplicated field:", e.getDuplicatedFields())
                .type(URI.create(req.getRequestURI()))
                .build();
    }

    @ExceptionHandler(AlbumNotFoundException.class)
    public ErrorResponse albumNotFoundException(AlbumNotFoundException e,
                                                    HttpServletRequest req) {
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage())
                .title(e.getMessage())
                .property("Album Id: ", e.getAlbumId())
                .type(URI.create(req.getRequestURI()))
                .build();
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ErrorResponse PostNotFoundException(PostNotFoundException e,
                                                HttpServletRequest req) {
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage())
                .title(e.getMessage())
                .property("Post Id: ", e.getPostId())
                .type(URI.create(req.getRequestURI()))
                .build();
    }

    @ExceptionHandler(PhotoNotFoundException.class)
    public ErrorResponse PhotoNotFoundException(PhotoNotFoundException e,
                                               HttpServletRequest req) {
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage())
                .title(e.getMessage())
                .property("Photo Id: ", e.getPhotoId())
                .type(URI.create(req.getRequestURI()))
                .build();
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ErrorResponse NotAuthorizedException(NotAuthorizedException e,
                                                HttpServletRequest req) {
        return ErrorResponse.builder(e, HttpStatus.FORBIDDEN, e.getMessage())
                .title(e.getMessage())
                .type(URI.create(req.getRequestURI()))
                .build();
    }

    @ExceptionHandler(AlbumWithPhotoException.class)
    public ErrorResponse AlbumWithPhotoException(AlbumWithPhotoException e,
                                                HttpServletRequest req) {
        return ErrorResponse.builder(e, HttpStatus.FORBIDDEN, e.getMessage())
                .title(e.getMessage())
                .property("Photos in this album: ", e.photos)
                .type(URI.create(req.getRequestURI()))
                .build();
    }
}
