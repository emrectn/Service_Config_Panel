package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PermType;
import com.example.demo.repository.PermTypeRepository;
import com.example.demo.service.PermTypeService;

@Service("permTypeService")
public class PermTypeServiceImpl implements PermTypeService{
	
	@Autowired
	private PermTypeRepository permTypeRepository;
	
	@Override
	public boolean isPermTypeExist(PermType permType) {
		return permTypeRepository.findByPermname(permType.getPermname()) != null;
	}

	@Override
	public List<PermType> findAll() {
		return permTypeRepository.findAll();
	}

	@Override
	public PermType findById(Integer id) {
		return permTypeRepository.getOne(id);	
	}

	@Override
	public void save(PermType permType) {
		permTypeRepository.save(permType);
	}

	@Override
	public void deleteById(Integer id) {
		permTypeRepository.deleteById(id);
	}

	@Override
	public void deleteAllUsers() {
		permTypeRepository.deleteAll();
		
	}
	
	
}
