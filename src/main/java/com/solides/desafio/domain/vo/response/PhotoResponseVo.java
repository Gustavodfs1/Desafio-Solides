package com.solides.desafio.domain.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoResponseVo {

    private Integer id;
    private String photoPath;
    private Integer idAlbum;
}
