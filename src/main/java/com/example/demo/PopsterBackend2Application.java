package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class PopsterBackend2Application {

	public static void main(String[] args) {
		SpringApplication.run(PopsterBackend2Application.class, args);
	}
	public WebMvcConfigurer corsConfig(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("api/v1/user").allowedOrigins("http://localhost:8080");
				registry.addMapping("api/v1/file").allowedOrigins("http://localhost:8080");
			}
		};
	}

}
