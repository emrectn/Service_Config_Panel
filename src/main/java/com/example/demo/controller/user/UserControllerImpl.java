package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.service.UserService;

public class UserControllerImpl implements UserController {
	
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<String> addUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> updateUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
