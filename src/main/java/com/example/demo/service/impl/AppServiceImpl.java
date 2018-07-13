package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CostType;
import com.example.demo.model.PermType;
import com.example.demo.model.StatusType;
import com.example.demo.repository.ConfigurationRepository;
import com.example.demo.service.AppService;

@Service("appService")
public class AppServiceImpl implements AppService {

	@Override
	public List<CostType> editCostTypeList(List<CostType> ctypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PermType> editPermTypeList(List<PermType> ptypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatusType> editStatusTypeList(List<StatusType> stypes) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
