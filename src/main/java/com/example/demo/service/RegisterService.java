package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Register;
import com.example.demo.model.User;

public interface RegisterService {
	
	List<Register> findAll();
	
	Register findById(Integer id);
	
	void deleteById(Integer id);
	
	List<Register> findByTeamId(User user);

	void save(Register register);
}
