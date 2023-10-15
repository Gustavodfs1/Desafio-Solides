package com.solides.desafio.fixture.vo;

import com.solides.desafio.domain.vo.request.AlbumRequestVo;

public class AlbumRequestVoFixture {

    public static AlbumRequestVo givenAlbumRequestVoDefault() {
        return AlbumRequestVo.builder()
                .nameAlbum("Ferias")
                .id(123)
                .build();
    }
}
