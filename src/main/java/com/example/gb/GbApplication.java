package com.example.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GbApplication {

	public static void main(String[] args) {
		SpringApplication.run(GbApplication.class, args);
	}

}
