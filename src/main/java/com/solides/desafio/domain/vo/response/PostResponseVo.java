package com.solides.desafio.domain.vo.response;

import com.solides.desafio.constants.TypePost;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseVo {

    private Integer id;
    private String value;
    private TypePost type;
    private LocalDateTime datePost;
    private Integer userId;
    private Integer postId;
}
