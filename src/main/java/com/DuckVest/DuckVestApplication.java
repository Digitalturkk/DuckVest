package com.DuckVest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DuckVestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuckVestApplication.class, args);
	}

}
