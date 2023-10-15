package com.solides.desafio.fixture;

import com.solides.desafio.domain.Album;

public class AlbumFixture {

    public static Album givenAlbumDefault() {
        return Album.builder()
                .nameAlbum("Ferias")
                .id(123)
                .user(UserFixture.givenUserDefault())
                .build();
    }
}
