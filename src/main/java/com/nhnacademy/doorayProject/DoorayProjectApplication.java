package com.nhnacademy.doorayProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.nhnacademy.doorayProject.repository")
@SpringBootApplication
public class DoorayProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoorayProjectApplication.class, args);
	}

}
