package com.solides.desafio.fixture.vo;

import com.solides.desafio.domain.vo.request.SignUpRequestVo;

public class SignUpRequestVoFixture {

    public static SignUpRequestVo givenSignUpRequestVoDefault() {
        return new SignUpRequestVo("desafio@api.com", "api", "123");
    }

}
