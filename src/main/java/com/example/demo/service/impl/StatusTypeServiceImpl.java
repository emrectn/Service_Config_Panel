package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.StatusType;
import com.example.demo.repository.StatusTypeRepository;
import com.example.demo.service.StatusTypeService;

@Service("statusTypeService")
public class StatusTypeServiceImpl implements StatusTypeService{

	@Autowired
	private StatusTypeRepository statusTypeRepository;
	
	@Override
	public boolean isStatusTypeExist(StatusType statusType) {
		return statusTypeRepository.findByStatusname(statusType.getStatusname()) != null;
	}

	@Override
	public List<StatusType> findAll() {
		return statusTypeRepository.findAll();
	}

	@Override
	public StatusType findById(Integer id) {
		return statusTypeRepository.getOne(id);
	}

	@Override
	public StatusType save(StatusType statusType) {
		statusTypeRepository.save(statusType);
		return statusType;
	}

	@Override
	public void deleteById(Integer id) {	
		statusTypeRepository.deleteById(id);
	}

	@Override
	public void deleteAllUsers() {
		statusTypeRepository.deleteAll();
	}

}