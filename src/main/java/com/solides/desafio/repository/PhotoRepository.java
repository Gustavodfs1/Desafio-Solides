package com.solides.desafio.repository;

import com.solides.desafio.domain.Photo;
import com.solides.desafio.domain.vo.response.PhotoResponseVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    @Query("select new com.solides.desafio.domain.vo.response.PhotoResponseVo(p.id, p.photoPath, p.album.id) FROM Photo p")
    List<PhotoResponseVo> find();

    @Query("select new com.solides.desafio.domain.vo.response.PhotoResponseVo(p.id, p.photoPath, p.album.id) FROM Photo p where p.album.id = :id")
    List<PhotoResponseVo> findAllByAlbum(@Param("id") Integer albumId);
}
