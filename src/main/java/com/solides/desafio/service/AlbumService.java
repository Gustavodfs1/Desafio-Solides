package com.solides.desafio.service;

import com.solides.desafio.domain.Album;
import com.solides.desafio.domain.User;
import com.solides.desafio.domain.vo.request.AlbumRequestVo;
import com.solides.desafio.domain.vo.response.AlbumResponseVo;
import com.solides.desafio.exception.AlbumNotFoundException;
import com.solides.desafio.exception.AlbumWithPhotoException;
import com.solides.desafio.exception.NotAuthorizedException;
import com.solides.desafio.exception.PostNotFoundException;
import com.solides.desafio.repository.AlbumRepository;
import com.solides.desafio.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final PhotoRepository photoRepository;

    public AlbumResponseVo createAlbum(AlbumRequestVo albumRequestVo, User user){
        Album album = new Album();
        album.setNameAlbum(albumRequestVo.getNameAlbum());
        album.setUser(user);

        var albumSaved = albumRepository.save(album);

        return new AlbumResponseVo(albumSaved.getNameAlbum(), albumSaved.getUser().getId(), albumSaved.getId());
    }

    public AlbumResponseVo getAlbum(Integer albumId) {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));
        return new AlbumResponseVo(album.getNameAlbum(), album.getUser().getId(), album.getId());
    }

    public void deleteAlbum(Integer albumId, User user) {
        var album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));
        if (album.getUser().getId().equals(user.getId())) {
            try {
                albumRepository.deleteById(albumId);
            } catch (DataIntegrityViolationException e){
                var photos = photoRepository.findAllByAlbum(albumId);
              throw new AlbumWithPhotoException(albumId, photos);
            }
        } else {
            throw new NotAuthorizedException();
        }
    }
}
