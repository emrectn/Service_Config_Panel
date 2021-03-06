package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AppConfiguration;

@Repository("configurationRepository")
public interface ConfigurationRepository extends JpaRepository<AppConfiguration, Long> {
	
	List<AppConfiguration> findByName(String name);
	
	void deleteById(Long id);
	
	AppConfiguration findByNameAndIsActiveIsTrue(String name);
}
