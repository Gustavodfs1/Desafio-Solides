package com.solides.desafio.controller;

import com.solides.desafio.domain.vo.response.AlbumResponseVo;
import com.solides.desafio.domain.vo.response.PhotoResponseVo;
import com.solides.desafio.domain.User;
import com.solides.desafio.domain.vo.request.AlbumRequestVo;
import com.solides.desafio.domain.vo.request.PhotoRequestVo;
import com.solides.desafio.repository.AlbumRepository;
import com.solides.desafio.service.AlbumService;
import com.solides.desafio.service.PhotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/album")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final PhotoService photoService;
    private final AlbumRepository albumRepository;

    @PostMapping
    public ResponseEntity<AlbumResponseVo> post(@Valid @RequestBody AlbumRequestVo albumRequestVo) {
        var user = getPrincipal();
        return new ResponseEntity<>(albumService.createAlbum(albumRequestVo, user), HttpStatus.CREATED);
    }

    @PostMapping("/{albumId}/photo")
    public ResponseEntity<PhotoResponseVo> addPhoto(@Valid @RequestBody PhotoRequestVo photoRequestVo, @PathVariable Integer albumId) {
        var user = getPrincipal();
        return new ResponseEntity<>(photoService.postPhoto(photoRequestVo, albumId, user), HttpStatus.CREATED);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumResponseVo> getAlbum(@PathVariable Integer albumId) {

        return new ResponseEntity<>(albumService.getAlbum(albumId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseVo>> getAlbums() {

        return new ResponseEntity<>(albumRepository.getAlbums(), HttpStatus.OK);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<HttpStatus> deleteAlbum(@PathVariable Integer albumId) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        albumService.deleteAlbum(albumId, user);
        return ResponseEntity.ok().build();
    }

    private static User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
