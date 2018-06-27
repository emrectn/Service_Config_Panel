package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PermType;

@Repository("permTypeRepository")
public interface PermTypeRepository extends JpaRepository<PermType, Integer> {
	
	PermType findByPermname(String permName);

	List<PermType> findAll();

}
