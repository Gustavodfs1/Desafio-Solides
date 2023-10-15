package com.solides.desafio.service;

import com.solides.desafio.domain.Photo;
import com.solides.desafio.domain.vo.response.PhotoResponseVo;
import com.solides.desafio.domain.User;
import com.solides.desafio.domain.vo.request.PhotoRequestVo;
import com.solides.desafio.exception.AlbumNotFoundException;
import com.solides.desafio.exception.NotAuthorizedException;
import com.solides.desafio.exception.PhotoNotFoundException;
import com.solides.desafio.repository.AlbumRepository;
import com.solides.desafio.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    public PhotoResponseVo postPhoto(PhotoRequestVo photoRequestVo, Integer albumId, User user) {
        Photo photo = new Photo();
        photo.setPhotoPath(photoRequestVo.getPhotoPath());
        var album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));
        if(!Objects.equals(album.getUser().getId(), user.getId())) {
            throw new BadCredentialsException("You can add photos only in your album.");
        }
        photo.setAlbum(album);

        var photoSaved = photoRepository.save(photo);

        PhotoResponseVo photoResponse = new PhotoResponseVo();
        photoResponse.setIdAlbum(photoSaved.getAlbum().getId());
        photoResponse.setPhotoPath(photoSaved.getPhotoPath());
        photoResponse.setId(photoSaved.getId());

        return photoResponse;
    }

    public PhotoResponseVo getPhoto(Integer photoId){
        try {
            var photo = photoRepository.findById(photoId);

            PhotoResponseVo photoResponse = new PhotoResponseVo();
            photoResponse.setPhotoPath(photo.get().getPhotoPath());
            photoResponse.setId(photo.get().getId());
            photoResponse.setIdAlbum(photo.get().getAlbum().getId());

            return photoResponse;
        } catch (Exception e){
            throw new PhotoNotFoundException(photoId);
        }
    }

    public void deletePhoto(Integer photoId, User user) {
        var photo = photoRepository.findById(photoId).orElseThrow(() -> new PhotoNotFoundException(photoId));
        if (photo.getAlbum().getUser().getId().equals(user.getId())) {
            photoRepository.deleteById(photoId);
        } else {
            throw new NotAuthorizedException();
        }
    }
}
