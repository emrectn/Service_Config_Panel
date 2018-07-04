package com.example.demo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.service.UserService;


public class AuthServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserService userService;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.example.demo.model.User activeUser = userService.findUserByEmail(email);		
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getPermtype().getPermname());
		UserDetails userDetails = (UserDetails) new User(activeUser.getEmail(), activeUser.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
	
	

}
