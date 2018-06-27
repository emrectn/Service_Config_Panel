package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StatusType;

@Repository("statusTypeRepository")
public interface StatusTypeRepository extends JpaRepository<StatusType, Integer> {

	StatusType findByStatusname(String statusName);
	
	List<StatusType> findAll();
	

}
