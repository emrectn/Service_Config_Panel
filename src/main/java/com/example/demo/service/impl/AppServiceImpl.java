package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ConfigurationRepository;
import com.example.demo.service.AppService;

@Service("appService")
public class AppServiceImpl implements AppService {
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Override
	public void saveAllData() {
		configurationRepository.deleteAll();
		System.out.println("SAVED ALL DATA");
	}
	

}
