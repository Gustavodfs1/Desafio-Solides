package com.solides.desafio.service;

import com.solides.desafio.domain.Post;
import com.solides.desafio.domain.User;
import com.solides.desafio.domain.vo.request.PostRequestVo;
import com.solides.desafio.domain.vo.response.PostResponseVo;
import com.solides.desafio.repository.PostRepository;
import com.solides.desafio.repository.UserRepository;
import com.solides.desafio.fixture.vo.PostRequestVoFixture;
import com.solides.desafio.fixture.vo.PostResponseFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.solides.desafio.fixture.UserFixture.givenUserDefault;
import static com.solides.desafio.fixture.vo.PostFixture.givenPostDeafult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PostServiceTest {


    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void givenDataWhenPostPhotoThenReturnPostResponseVo() {
        User user = givenUserDefault();
        Post post = givenPostDeafult();
        PostResponseVo postResponseVo = PostResponseFixture.givenPostResponseDeafult();
        PostRequestVo postRequestVo = PostRequestVoFixture.givenPostRequestVoDeafult();

        when(userRepository.getReferenceById(1)).thenReturn(user);
        when(postRepository.save(any())).thenReturn(post);

        PostResponseVo result = postService.post(postRequestVo, user);

        verify(userRepository).getReferenceById(123);
        verify(postRepository).save(any(Post.class));

        assertEquals(postResponseVo.getValue(), result.getValue());
        assertNotNull(result.getId());
    }

    @Test
    void givenDataWhenPostCommentPhotoThenReturnPostResponseVo() {
        User user = givenUserDefault();
        Post post = givenPostDeafult();
        PostResponseVo postResponseVo = PostResponseFixture.givenPostResponseDeafult();
        PostRequestVo postRequestVo = PostRequestVoFixture.givenPostRequestVoDeafult();
        Integer postId = 123;

        when(userRepository.getReferenceById(1)).thenReturn(user);
        when(postRepository.save(any())).thenReturn(post);

        PostResponseVo result = postService.postComment(postRequestVo,postId, user);

        verify(userRepository).getReferenceById(123);
        verify(postRepository).save(any(Post.class));

        assertEquals(postResponseVo.getValue(), result.getValue());
        assertNotNull(result.getId());
    }
}