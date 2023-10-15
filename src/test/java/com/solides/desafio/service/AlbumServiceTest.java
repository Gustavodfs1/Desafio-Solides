package com.solides.desafio.service;

import com.solides.desafio.domain.Album;
import com.solides.desafio.domain.User;
import com.solides.desafio.domain.vo.request.AlbumRequestVo;
import com.solides.desafio.domain.vo.response.AlbumResponseVo;
import com.solides.desafio.exception.NotAuthorizedException;
import com.solides.desafio.repository.AlbumRepository;
import com.solides.desafio.fixture.vo.AlbumRequestVoFixture;
import com.solides.desafio.fixture.vo.AlbumResponseFixture;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AlbumServiceTest {

    @InjectMocks
    private AlbumService albumService;
    @Mock
    private AlbumRepository albumRepository;

    @Test
    void givenAlbumRequestVoWhenCreatAlbumThenReturnalbumResponse() {
        AlbumRequestVo albumRequestVoFixture = AlbumRequestVoFixture.givenAlbumRequestVoDefault();
        User user = givenUserDefault();
        AlbumResponseVo albumResponseVo = AlbumResponseFixture.givenAlbumResponseDefault();
        Album album = givenAlbumDefault();

        when(albumRepository.save(any())).thenReturn(album);

        final AlbumResponseVo result = albumService.createAlbum(albumRequestVoFixture, user);

        then(albumResponseVo).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void givenAlbumIdThenReturnAlbumResponseVo(){
        AlbumResponseVo albumResponseVo = AlbumResponseFixture.givenAlbumResponseDefault();
        Album album = givenAlbumDefault();
        var albumId = 123;

        when(albumRepository.findById(any())).thenReturn(Optional.ofNullable(album));

        final AlbumResponseVo result = albumService.getAlbum(albumId);

        then(albumResponseVo).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void givenAlbumIdFromDifferentOwnerOnDeleteThenThrowException(){
        User user = givenDiferentUser();
        var albumId = 123;
        Album album = givenAlbumDefault();

        when(albumRepository.findById(any())).thenReturn(Optional.ofNullable(album));

        final NotAuthorizedException result = assertThrows(NotAuthorizedException.class, () -> albumService.deleteAlbum(albumId, user));

        then(result).isInstanceOf(NotAuthorizedException.class);
    }

    @Test
    void givenAlbumIdOnDeleteThenCallAlbumRepository(){
        User user = givenUserDefault();
        var albumId = 123;
        Album album = givenAlbumDefault();

        when(albumRepository.findById(any())).thenReturn(Optional.ofNullable(album));

        albumService.deleteAlbum(albumId, user);

        verify(albumRepository).deleteById(albumId);
    }
}