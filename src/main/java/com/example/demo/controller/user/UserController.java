package com.example.demo.controller.user;

import org.springframework.http.ResponseEntity;

public interface UserController {
	
	ResponseEntity<String> addUser();
	
	ResponseEntity<String> getUser();
	
	ResponseEntity<String> getAllUser();
	
	ResponseEntity<String> deleteUser();
	
	ResponseEntity<String> updateUser();

}
