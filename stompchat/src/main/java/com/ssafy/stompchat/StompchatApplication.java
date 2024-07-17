package com.ssafy.stompchat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition
@SpringBootApplication
public class StompchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(StompchatApplication.class, args);
	}

}
