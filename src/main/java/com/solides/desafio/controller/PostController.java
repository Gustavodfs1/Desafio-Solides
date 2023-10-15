package com.solides.desafio.controller;

import com.solides.desafio.domain.User;
import com.solides.desafio.domain.vo.response.PostResponseVo;
import com.solides.desafio.domain.vo.request.PostRequestVo;
import com.solides.desafio.repository.PostRepository;
import com.solides.desafio.service.PostService;
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
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping
    public ResponseEntity<PostResponseVo> post(@Valid @RequestBody PostRequestVo post) {
        var user = getPrincipal();
        return new ResponseEntity<>(postService.post(post, user), HttpStatus.CREATED);
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<PostResponseVo> postComment(@Valid @RequestBody PostRequestVo post, @PathVariable Integer postId) {
        var user = getPrincipal();
        return new ResponseEntity<>(postService.postComment(post, postId, user), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comment")
    public ResponseEntity<PostResponseVo> getComments(@PathVariable Integer postId) {
        return new ResponseEntity<>(postRepository.getComments(postId), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseVo> getPost(@PathVariable Integer postId) {

        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseVo>> getPosts() {

        return new ResponseEntity<>(postRepository.getPosts(), HttpStatus.OK);
    }

    private static User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Integer postId) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postService.deletePhoto(postId, user);
        return ResponseEntity.ok().build();
    }
}
