package com.nhnacademy.doorayProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ConfigurationPropertiesScan
@EnableJpaRepositories(basePackages = "com.nhnacademy.doorayProject.repository")
@SpringBootApplication
public class DoorayProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoorayProjectApplication.class, args);
	}

}
