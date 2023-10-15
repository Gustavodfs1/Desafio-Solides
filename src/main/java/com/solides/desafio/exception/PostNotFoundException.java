package com.solides.desafio.exception;

import lombok.Getter;

@Getter
public class PostNotFoundException extends RuntimeException {

    private final Integer postId;


    public PostNotFoundException(Integer postId) {
        super("Post not found.");
        this.postId = postId;
    }
}
