package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserTeam;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeam, Integer>{
	
	UserTeam findByTeamname(String teamName);
		
	List<UserTeam> findAll();

}
