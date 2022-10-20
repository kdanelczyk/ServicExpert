package com.kamil.servicExpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ServicExpertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicExpertApplication.class, args);
	}

}
