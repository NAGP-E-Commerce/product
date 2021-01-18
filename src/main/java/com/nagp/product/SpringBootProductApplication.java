package com.nagp.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProductApplication.class, args);
	}
}
