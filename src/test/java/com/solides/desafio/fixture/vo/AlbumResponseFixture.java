package com.solides.desafio.fixture.vo;

import com.solides.desafio.domain.vo.response.AlbumResponseVo;

public class AlbumResponseFixture {

    public static AlbumResponseVo givenAlbumResponseDefault() {
        return new AlbumResponseVo("Ferias", 123, 123);
    }
}
