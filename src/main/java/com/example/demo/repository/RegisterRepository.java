package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Register;
import com.example.demo.model.UserTeam;

@Repository("registerRepository")
public interface RegisterRepository extends JpaRepository<Register, Integer>{
	
	List<Register> findByRegisterUserteam(UserTeam teamId);
	
}
