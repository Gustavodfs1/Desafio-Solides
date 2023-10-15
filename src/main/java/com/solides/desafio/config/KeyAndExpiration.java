package com.solides.desafio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.security.jwt")
public record KeyAndExpiration(String secretKey, Long jwtExpirationInDays){
    }
