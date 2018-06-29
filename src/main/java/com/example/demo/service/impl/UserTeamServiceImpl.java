package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserTeam;
import com.example.demo.repository.UserTeamRepository;
import com.example.demo.service.UserTeamService;

@Service("userTeamService")
public class UserTeamServiceImpl implements UserTeamService{
	
	@Autowired
	private UserTeamRepository userTeamRepository;
	
	@Override
	public boolean isUserTeamExist(UserTeam userTeam) {
		return userTeamRepository.findByTeamname(userTeam.getTeamname()) != null;
	}

	@Override
	public List<UserTeam> findAll() {
		return userTeamRepository.findAll();
	}

	@Override
	public UserTeam findById(Integer id) {
		return userTeamRepository.getOne(id);	
	}

	@Override
	public void save(UserTeam userTeam) {
		userTeamRepository.save(userTeam);
	}

	@Override
	public void deleteById(Integer id) {
		userTeamRepository.deleteById(id);
	}

	@Override
	public void deleteAllUsers() {
		userTeamRepository.deleteAll();
		
	}
}
