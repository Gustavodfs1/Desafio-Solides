package com.solides.desafio.domain.vo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlbumRequestVo {

    private Integer id;

    private String nameAlbum;
}
