package com.example.demo.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomErrorType;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/app/api/user")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = userService.findAll();

		logger.error("Bakiniz : " + users);
		if (users.isEmpty()) {

			logger.error("Buradanda bakınız : " + new ResponseEntity(HttpStatus.NO_CONTENT));
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		logger.error("Şuradanda bakınız : " + users);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/app/api/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable Integer id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findUserById(id);
		try {
			System.out.println(user);
		} catch (EntityNotFoundException e) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	

}
