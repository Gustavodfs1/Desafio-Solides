package com.solides.desafio;

import com.solides.desafio.config.KeyAndExpiration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(value = {KeyAndExpiration.class})
@SpringBootApplication
public class GustavoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GustavoApplication.class, args);
	}

}
