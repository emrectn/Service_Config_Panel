package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class ConfigurationReader<T> {
	private String name;
	@Autowired
	private ServiceRepository serviceRepo;
	
	public ConfigurationReader(String name) {
		this.name = name;
	}
	
	public T getValue(String name) {	
		try {
			return (T) serviceRepo.findByName(name).get(0);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

}
