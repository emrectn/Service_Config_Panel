package com.example.demo.service;

import java.util.List;

import com.example.demo.model.PermType;

public interface PermTypeService {
	
	boolean isPermTypeExist(PermType permType);

	List<PermType> findAll();
	
	PermType findById(Integer id);
	
	PermType save(PermType permType);
	
	void deleteById(Integer id);
	
	void deleteAllUsers();

}
