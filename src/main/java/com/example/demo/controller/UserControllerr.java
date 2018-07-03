package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController
public class UserControllerr {
	
	public static final Logger logger = LoggerFactory.getLogger(PermTypeController.class);
	
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
	

}
