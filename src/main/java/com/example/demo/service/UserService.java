package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public interface UserService {
	
	public User findUserByEmailAndPassword(String email, String password);
	
	public User findUserByEmail(String email);
	
	public List<User> findAll();
	
	public UserDetails loadUserByUsername(String username, String password);
	
	public void saveUser(User user);
	
}
