package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AppException;
import com.example.demo.model.AppConfiguration;
import com.example.demo.repository.ConfigurationRepository;

@Service("configurationReader")
public class ConfigurationReaderImpl implements ConfigurationReader{

	@Autowired
	private ConfigurationRepository configurationRepository;

	@SuppressWarnings("unchecked")
	public <T> T getValue(String name) {
		
		if (name == null || name.equals("")) {
			throw new AppException("Name can not be null or empty!");
		}
		
		try {

			AppConfiguration appConfiguration = configurationRepository.findByNameAndIsActiveIsTrue(name);
			if (appConfiguration != null) {
				if (Integer.class.getName().equals(appConfiguration.getType())) {
					return (T) Integer.valueOf(appConfiguration.getValue());
				} else if (Boolean.class.getName().equals(appConfiguration.getType())) {
					return (T) Boolean.valueOf(appConfiguration.getValue());
				} else if (String.class.getName().equals(appConfiguration.getType())) {
					return (T) String.valueOf(appConfiguration.getValue());
				} else {
					return null;
				}
			} else {
				throw new AppException("The value " + name + "not found!");
			}
		} catch (Exception e) {
			throw new AppException("Invalid argument by name : " + name, e);
		}
	}

}
