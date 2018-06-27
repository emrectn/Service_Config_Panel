package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CostType;
import com.example.demo.repository.CostTypeRepository;

@RestController
public class CostTypeController {
	
	@Autowired
	private CostTypeRepository costTypeRepository;
	
	@GetMapping("/app/api/costtype/{id}")
	public Optional<CostType> getCostType(@PathVariable Integer id) {
		return costTypeRepository.findById(id);
	}
	
	@GetMapping("/app/api/costtype")
	public List<CostType> getAllCostType() {
		return costTypeRepository.findAll();
	}
	
	

}
