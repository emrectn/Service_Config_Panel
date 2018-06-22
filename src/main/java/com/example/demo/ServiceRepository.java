package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.ServiceConfig;

@Repository("serviceRepo")
public interface ServiceRepository extends JpaRepository<ServiceConfig, Long> {
	List<ServiceConfig> findByName(String name);
	void deleteById(Long id);
}
