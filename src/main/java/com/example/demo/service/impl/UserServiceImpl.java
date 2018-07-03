package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.PermType;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public User findByEmailAndPassword(String email, String password) {
		User user = new User();
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) userRepository.findByEmailAndPassword(email, password);
		
		if(!userList.isEmpty()) {
			user = userList.get(0);
		}
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email, String password) throws UsernameNotFoundException{
		User user = findByEmailAndPassword(email, password);
		return null;
	}

	@Override
	public User findByEmail(String email) {
		User user = findByEmail(email);
		return user;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
