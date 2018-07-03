package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public interface UserService {
	
	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);
	
	List<User> findAll();
	
	UserDetails loadUserByUsername(String username, String password);
	

}
