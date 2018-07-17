package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.config.PersistenceJPAConfig;
import com.example.demo.repository.PermTypeRepository;
import com.example.demo.service.PermTypeService;

@Configuration
@Import(PersistenceJPAConfig.class)
@SpringBootApplication(scanBasePackages = { "com.example.demo" })
public class MainApplication {
	
	@Autowired
	private static PermTypeService permTypeService;
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		System.out.println("Program Calisti");
		
		System.out.println(permTypeService.findAll());
		
	}
}
