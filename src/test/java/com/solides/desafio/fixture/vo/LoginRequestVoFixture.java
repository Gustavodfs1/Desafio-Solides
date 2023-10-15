package com.solides.desafio.fixture.vo;

import com.solides.desafio.domain.vo.request.LoginRequestVo;

public class LoginRequestVoFixture {

    public static LoginRequestVo givenLoginRequestVoDeafult(){
    return new LoginRequestVo("solides","desafio");
    }
}
