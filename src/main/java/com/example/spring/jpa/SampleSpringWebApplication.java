package com.example.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *  @author recklessN1nja
 */
@SpringBootApplication(scanBasePackages = "com.example.spring.jpa")
public class SampleSpringWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleSpringWebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleSpringWebApplication.class, args);
	}

}