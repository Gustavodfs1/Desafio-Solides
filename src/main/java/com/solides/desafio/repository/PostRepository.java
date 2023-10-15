package com.solides.desafio.repository;

import com.solides.desafio.domain.Post;
import com.solides.desafio.domain.vo.response.PostResponseVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select new com.solides.desafio.domain.vo.response.PostResponseVo(post.id, post.value, post.type, post.datePost, post.user.id, post.post.id) from Post post")
    List<PostResponseVo> getPosts();

    @Query("select new com.solides.desafio.domain.vo.response.PostResponseVo(post.id, post.value, post.type, post.datePost, post.user.id, post.post.id) from Post post where post.post.id = :id")
    PostResponseVo getComments(@Param("id") Integer id);
}
