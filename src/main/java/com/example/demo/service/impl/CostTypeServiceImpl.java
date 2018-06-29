package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.CostType;
import com.example.demo.repository.CostTypeRepository;
import com.example.demo.service.CostTypeService;

@Service("costTypeService")
public class CostTypeServiceImpl implements CostTypeService{
	
	@Autowired
	private CostTypeRepository costTypeRepository;
	
	@Override
	public boolean isCostTypeExist(CostType costType) {
		return costTypeRepository.findByCostname(costType.getCostname()) != null;
	}

	@Override
	public List<CostType> findAll() {
		return costTypeRepository.findAll();
	}


	@Override
	public void save(CostType costType) {
		costTypeRepository.save(costType);
	}

	@Override
	public void deleteById(Integer id) {
		costTypeRepository.deleteById(id);
	}

	@Override
	public void deleteAllUsers() {
		costTypeRepository.deleteAll();
		
	}

	@Override
	public CostType findById(Integer id) {
		return costTypeRepository.getOne(id);	
	}

}
