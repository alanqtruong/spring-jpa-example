package com.example.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @author alanqtruong
 */
@SpringBootApplication(scanBasePackages = "com.example.spring.jpa")
public class SampleSpringWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringWebApplication.class, args);
	}
}