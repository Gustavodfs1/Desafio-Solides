package com.solides.desafio.domain.vo.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestVo(
        @NotBlank String username,
        @NotBlank String password) {}
