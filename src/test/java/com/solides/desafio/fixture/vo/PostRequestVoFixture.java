package com.solides.desafio.fixture.vo;

import com.solides.desafio.domain.vo.request.PostRequestVo;

public class PostRequestVoFixture {

    public static PostRequestVo givenPostRequestVoDeafult(){
        PostRequestVo postRequestVo = new PostRequestVo();
        postRequestVo.setValue("Test post");
        return postRequestVo;
    }
}
