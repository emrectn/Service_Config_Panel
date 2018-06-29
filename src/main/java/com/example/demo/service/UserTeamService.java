package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserTeam;

public interface UserTeamService {
	boolean isUserTeamExist(UserTeam userTeam);
	
	List<UserTeam> findAll();
	
	UserTeam findById(Integer id);
	
	void save(UserTeam userTeam);
	
	void deleteById(Integer id);
	
	void deleteAllUsers();
}
