package com.solides.desafio.service;

import com.solides.desafio.domain.Post;
import com.solides.desafio.domain.User;
import com.solides.desafio.domain.vo.response.PostResponseVo;
import com.solides.desafio.domain.vo.request.PostRequestVo;
import com.solides.desafio.exception.NotAuthorizedException;
import com.solides.desafio.exception.PostNotFoundException;
import com.solides.desafio.repository.PostRepository;
import com.solides.desafio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseVo post(PostRequestVo postRequestVo, User user){
        Post post = new Post();
        post.setValue(postRequestVo.getValue());
        post.setType(postRequestVo.getType());
        post.setDatePost(LocalDateTime.now());
        var userSaved = userRepository.getReferenceById(user.getId());
        post.setUser(userSaved);

        var postSaved = postRepository.save(post);

        return createPostResponse(postSaved);
    }

    public PostResponseVo postComment(PostRequestVo postRequestVo, Integer postId, User user) {
        Post comment = new Post();
        comment.setValue(postRequestVo.getValue());
        comment.setType(postRequestVo.getType());
        comment.setDatePost(LocalDateTime.now());
        comment.setPost(postRepository.getReferenceById(postId));
        var userSaved = userRepository.getReferenceById(user.getId());
        comment.setUser(userSaved);

        var commentSaved = postRepository.save(comment);

        PostResponseVo postResponse = createPostResponse(commentSaved);
        postResponse.setPostId(postId);

        return postResponse;
    }

    public PostResponseVo getPost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        return new PostResponseVo(post.getId(), post.getValue(),post.getType(), post.getDatePost(), post.getUser().getId(), post.getId());
    }

    public void deletePhoto(Integer postId, User user) {
        var post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        if (post.getUser().getId().equals(user.getId())) {
            postRepository.deleteById(postId);
        } else {
            throw new NotAuthorizedException();
        }
    }

    private PostResponseVo createPostResponse(Post saved) {
        PostResponseVo postResponseVo = new PostResponseVo();
        postResponseVo.setDatePost(saved.getDatePost());
        postResponseVo.setValue(saved.getValue());
        postResponseVo.setType(saved.getType());
        postResponseVo.setUserId(saved.getUser().getId());
        postResponseVo.setId(saved.getId());

        return postResponseVo;
    }
}
