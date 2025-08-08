package com.AuthorApi.AuthorApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AuthorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorApiApplication.class, args);
	}

}
