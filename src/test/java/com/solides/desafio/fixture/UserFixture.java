package com.solides.desafio.fixture;

import com.solides.desafio.domain.User;

public class UserFixture {

    public static User givenUserDefault() {
        return User.builder()
                .username("gustavo")
                .email("desafio@api.com.br")
                .password("pass")
                .id(123)
                .build();
    }

    public static User givenDiferentUser() {
        return User.builder()
                .email("desafio@api.com.br")
                .password("admin")
                .id(124)
                .build();
    }
}
