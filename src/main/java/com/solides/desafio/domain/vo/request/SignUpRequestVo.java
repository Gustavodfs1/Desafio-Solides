package com.solides.desafio.domain.vo.request;

import jakarta.validation.constraints.NotBlank;

public record SignUpRequestVo(
        @NotBlank String email,
        @NotBlank String username,
        @NotBlank String password
        ) {
}
