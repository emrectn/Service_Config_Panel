package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CostType;

@Repository("costTypeRepository")
public interface CostTypeRepository extends JpaRepository<CostType, Integer>{
	
	CostType findByCostname(String costName);
		
	List<CostType> findAll();

}
