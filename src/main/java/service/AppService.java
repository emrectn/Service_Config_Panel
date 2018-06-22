package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.ServiceRepository;
import com.spring.model.ServiceConfig;

public class AppService {
	
	@Autowired
	private ServiceRepository serviceRepo;
	
	public Collection<ServiceConfig> getAllBooks(){
		List<ServiceConfig> configs = new ArrayList<ServiceConfig>();
		for (ServiceConfig serviceConfig : serviceRepo.findAll()) {
			configs.add(serviceConfig);
		}
		return configs;
	}
}
