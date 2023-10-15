package com.solides.desafio.fixture.vo;

import com.solides.desafio.domain.vo.response.PostResponseVo;

import java.time.LocalDateTime;

public class PostResponseFixture {

    public static PostResponseVo givenPostResponseDeafult(){
        PostResponseVo postResponse = new PostResponseVo();
        postResponse.setDatePost(LocalDateTime.now());
        postResponse.setValue("Test post");

        return  postResponse;
    }
}
