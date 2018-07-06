package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Register;
import com.example.demo.repository.RegisterRepository;
import com.example.demo.service.RegisterService;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Override
	public List<Register> findAll() {
		return registerRepository.findAll();
	}

	@Override
	public Register findById(Integer id) {
		return registerRepository.getOne(id);
	}

	@Override
	public List<Register> findByTeamId(Integer id) {
		return null;
	}

}
