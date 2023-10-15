package com.solides.desafio.exception;

import com.solides.desafio.domain.vo.response.PhotoResponseVo;
import lombok.Getter;

import java.util.List;

@Getter
public class AlbumWithPhotoException extends RuntimeException {

    private final Integer albumId;
    List<PhotoResponseVo> photos;



    public AlbumWithPhotoException(Integer albumId, List<PhotoResponseVo> photos) {
        super("This album still contains photos and cannot be deleted. Only empty albums can be deleted");
        this.albumId = albumId;
        this.photos = photos;
    }
}
