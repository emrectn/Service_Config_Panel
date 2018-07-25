package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.demo.config.PersistenceJPAConfig;

@Configuration
@Import(PersistenceJPAConfig.class)
@SpringBootApplication(scanBasePackages = { "com.example.demo" })
public class MainApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		System.out.println("Program Calisti");
		
	}
	
}
