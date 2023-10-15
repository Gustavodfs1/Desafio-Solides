package com.solides.desafio.fixture.vo;

import com.solides.desafio.domain.Post;

import static com.solides.desafio.fixture.UserFixture.givenUserDefault;

public class PostFixture {

    public static Post givenPostDeafult(){
        return Post.builder()
                .user(givenUserDefault())
                .id(123)
                .value("Test post")
                .build();
    }
}
