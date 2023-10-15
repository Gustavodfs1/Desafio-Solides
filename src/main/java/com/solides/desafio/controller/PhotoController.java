package com.solides.desafio.controller;

import com.solides.desafio.domain.Photo;
import com.solides.desafio.domain.vo.response.PhotoResponseVo;
import com.solides.desafio.domain.User;
import com.solides.desafio.repository.PhotoRepository;
import com.solides.desafio.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {


    private final PhotoService photoService;
    private final PhotoRepository photoRepository;

    @GetMapping
    public ResponseEntity<List<PhotoResponseVo>> getAllPhotos() {
        return new ResponseEntity<>(photoRepository.find(), HttpStatus.OK);
    }

    @GetMapping("/{photoId}")
    public ResponseEntity<PhotoResponseVo> getPhoto(@PathVariable Integer photoId) {
        return new ResponseEntity<>(photoService.getPhoto(photoId), HttpStatus.OK);
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity<HttpStatus> deletePhoto(@PathVariable Integer photoId) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        photoService.deletePhoto(photoId, user);
        return ResponseEntity.ok().build();
    }
}
