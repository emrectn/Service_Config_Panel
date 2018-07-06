package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Register;

public interface RegisterService {
	
	List<Register> findAll();
	
	Register findById(Integer id);
	
	List<Register> findByTeamId(Integer id);
	
}
