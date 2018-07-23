package com.example.demo.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository userRepository;

	@Autowired
    private RoleRepository roleRepository;
	
	@Override
	public User findUserByEmailAndPassword(String email, String password) {

		User user = userRepository.findByEmailAndPassword(email, password);
		
		if(user == null) {
			return null;
		}
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email, String password) throws UsernameNotFoundException{
		User user = findUserByEmailAndPassword(email, password);
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(user.getPassword());
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public User findUserById(Integer id) {
		return userRepository.getOne(id);
	}

}
