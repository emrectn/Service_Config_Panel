package com.example.demo.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.exception.CustomErrorType;
import com.example.demo.model.Register;
import com.example.demo.service.RegisterService;

@RestController
public class RegisterController {

	public static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private RegisterService registerService;
	
	@GetMapping("/app/api/register/{id}")
	public ResponseEntity<?> getRegister(@PathVariable Integer id) {
		logger.info("Fetching Register with id {}", id);
		Register register = registerService.findById(id);
		try {
			System.out.println(register);
		} catch (EntityNotFoundException e) {
			logger.error("Register with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Register with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Register>(register, HttpStatus.OK);
	}

	@GetMapping("/app/api/register")
	public ResponseEntity<List<Register>> getAllRegister() {
		List<Register> registers = registerService.findAll();
		if (registers.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Register>>(registers, HttpStatus.OK);
	}
	
	@PostMapping("/app/api/register")
	public ResponseEntity<?> createRegister(@RequestBody Register register, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Register : {}", register);
		
		registerService.save(register);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/app/api/register/{id}").buildAndExpand(register.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	

}
