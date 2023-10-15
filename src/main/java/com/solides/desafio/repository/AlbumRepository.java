package com.solides.desafio.repository;

import com.solides.desafio.domain.Album;
import com.solides.desafio.domain.vo.response.AlbumResponseVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    @Query("select new com.solides.desafio.domain.vo.response.AlbumResponseVo(a.nameAlbum, a.user.id, a.id) from Album a")
    List<AlbumResponseVo> getAlbums();
}
