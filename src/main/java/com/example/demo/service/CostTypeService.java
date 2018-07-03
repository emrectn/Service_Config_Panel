package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CostType;

public interface CostTypeService {
	
	boolean isCostTypeExist(CostType costType);
	
	List<CostType> findAll();
	
	CostType findById(Integer id);
	
	void save(CostType costType);
	
	void deleteById(Integer id);
	
	void deleteAllUsers();
}
