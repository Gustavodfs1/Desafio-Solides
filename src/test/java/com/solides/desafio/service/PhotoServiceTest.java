package com.solides.desafio.service;

import com.solides.desafio.domain.Album;
import com.solides.desafio.domain.Photo;
import com.solides.desafio.domain.User;
import com.solides.desafio.domain.vo.request.PhotoRequestVo;
import com.solides.desafio.domain.vo.response.PhotoResponseVo;
import com.solides.desafio.repository.AlbumRepository;
import com.solides.desafio.repository.PhotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.solides.desafio.fixture.AlbumFixture.givenAlbumDefault;
import static com.solides.desafio.fixture.UserFixture.givenDiferentUser;
import static com.solides.desafio.fixture.UserFixture.givenUserDefault;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PhotoServiceTest {
    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoService photoService;

    @Test
    void givenDataWhenPostPhotoThenReturnPhotoResponse() {
        PhotoRequestVo photoRequestVo = new PhotoRequestVo();
        photoRequestVo.setPhotoPath("path/to/photo.jpg");
        Integer albumId = 123;
        User user = givenUserDefault();

        Album album = givenAlbumDefault();

        Photo savedPhoto = new Photo();
        savedPhoto.setId(1);
        savedPhoto.setPhotoPath(photoRequestVo.getPhotoPath());
        savedPhoto.setAlbum(album);

        PhotoResponseVo expectedResponse = new PhotoResponseVo();
        expectedResponse.setIdAlbum(albumId);
        expectedResponse.setPhotoPath(photoRequestVo.getPhotoPath());
        expectedResponse.setId(savedPhoto.getId());

        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));
        when(photoRepository.save(any())).thenReturn(savedPhoto);

        PhotoResponseVo actualResponse = photoService.postPhoto(photoRequestVo, albumId, user);

        Assertions.assertEquals(expectedResponse.getIdAlbum(), actualResponse.getIdAlbum());
        Assertions.assertEquals(expectedResponse.getPhotoPath(), actualResponse.getPhotoPath());
        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());

        verify(albumRepository, times(1)).findById(albumId);
    }

    @Test
    void givenDataWithOtherUserWhenPostPhotoThenReturnException() {
        PhotoRequestVo photoRequestVo = new PhotoRequestVo();
        photoRequestVo.setPhotoPath("path/to/photo.jpg");
        Integer albumId = 123;
        User user = givenUserDefault();
        User user2 = givenDiferentUser();

        Album album = givenAlbumDefault();

        Photo savedPhoto = new Photo();
        savedPhoto.setId(1);
        savedPhoto.setPhotoPath(photoRequestVo.getPhotoPath());
        savedPhoto.setAlbum(album);

        PhotoResponseVo expectedResponse = new PhotoResponseVo();
        expectedResponse.setIdAlbum(albumId);
        expectedResponse.setPhotoPath(photoRequestVo.getPhotoPath());
        expectedResponse.setId(savedPhoto.getId());

        when(albumRepository.findById(albumId)).thenReturn(Optional.of(album));
        when(photoRepository.save(any())).thenReturn(savedPhoto);

        final BadCredentialsException result = assertThrows(BadCredentialsException.class, () -> photoService.postPhoto(photoRequestVo, albumId, user2));

        then(result).isInstanceOf(BadCredentialsException.class);
    }
}