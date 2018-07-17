package com.example.demo.service;

import java.util.List;

import com.example.demo.model.StatusType;

public interface StatusTypeService {
	
	boolean isStatusTypeExist(StatusType statusType);
	
	List<StatusType> findAll();
	
	StatusType findById(Integer id);
	
	StatusType save(StatusType statusType);
	
	void deleteById(Integer id);
	
	void deleteAllUsers();

}
