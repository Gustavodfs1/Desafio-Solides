package com.solides.desafio.domain.vo.request;

import com.solides.desafio.constants.TypePost;
import lombok.Data;

@Data
public class PostRequestVo {

    private String value;
    private TypePost type;
}
