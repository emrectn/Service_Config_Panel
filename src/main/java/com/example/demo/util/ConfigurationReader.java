package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import com.example.demo.exception.DemoAppException;
import com.example.demo.model.AppConfiguration;
import com.example.demo.repository.ConfigurationRepository;

public class ConfigurationReader<T> {

	@Autowired
	private static ConfigurationRepository configurationRepository;

	public ConfigurationReader() {

	}

	@SuppressWarnings("unchecked")
	public static <T> T getValue(String name) {
		
		if (name == null || name.equals("")) {
			throw new DemoAppException("Name can not be null or empty!");
		}
		
		try {
			AppConfiguration appConfiguration = configurationRepository.findByNameAndIsActive(name, Boolean.TRUE);
			
			if (appConfiguration != null) {
				if (Integer.class.getName().equals(appConfiguration.getType())) {
					return (T) Integer.valueOf(appConfiguration.getValue());
				} else if (Boolean.class.getName().equals(appConfiguration.getType())) {
					return (T) Boolean.valueOf(appConfiguration.getValue());
				} else {
					return (T) String.valueOf(appConfiguration.getValue());
				}
			} else {
				throw new DemoAppException("The value " + name + "not found!");
			}
		} catch (Exception e) {
			throw new DemoAppException("Invalid argument by name : " + name, e);
		}
	}

}
