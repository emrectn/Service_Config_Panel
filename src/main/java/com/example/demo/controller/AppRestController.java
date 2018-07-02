package com.example.demo.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AppConfiguration;
import com.example.demo.repository.ConfigurationRepository;

@RestController
public class AppRestController {
	
	@Autowired
	private ConfigurationRepository configRepo;
	
	@PostMapping("/add")
	public String addConfig(@RequestBody AppConfiguration serviceConfig) {
		configRepo.save(serviceConfig);
		return serviceConfig.toString();
	}
	
	@PostMapping("/login")
	public String saylogin() {
		System.out.println("Giriş Yaptın");
		return null;
	}
	
	@PostMapping("/logout")
	public String saylogout() {
		System.out.println("Çıkış Yaptın");
		return null;
	}
	
	@RequestMapping("/get")
	public String getValue(@RequestParam("name") String name) {	
		
		if (!configRepo.findByName(name).isEmpty() && configRepo.findByName(name).get(0) != null)
			return configRepo.findByName(name).get(0).getValue();
		return "{'status':'error'}";
	}
			
	@RequestMapping("/save")
	public String saveData() {
		// save a single Service
		configRepo.save(new AppConfiguration("yeni", getFakeType(), "etiya.com", true, "applicationName"));
		
		// save a list of Services
		configRepo.save(new AppConfiguration("yeni1", getFakeType(), "5", true, "applicationName1"));
		configRepo.save(new AppConfiguration("yeni2", getFakeType(), "50", true, "applicationName2"));
		configRepo.save(new AppConfiguration("yeni3", getFakeType(), "1", true, "applicationName3"));
		configRepo.save(new AppConfiguration("yeni4", getFakeType(), "emre cetin", true, "applicationName4"));
		return "Done";
	}
	
	@RequestMapping("/findall")
	public Collection<AppConfiguration> findAll() {
		return configRepo.findAll();
	}
	
	@GetMapping("/delete")
	public String deleteConfig(@RequestParam("id") long id) {
		try {
			configRepo.deleteById(id);
			
		}catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return "Previously deleted";
		}
		return "Done";
			
	}
	
	@RequestMapping("/findbyname")
	public String fetchDataByLastName(@RequestParam("name") String name){
		StringBuilder bld = new StringBuilder();
		
		for(AppConfiguration s: configRepo.findByName(name)){
			bld.append( s.toString() + "<br>"); 
		}
		
		if (bld.length() == 0)
			bld.append("Config Bulunamadi");
		
		return bld.toString();
	}
	
	public String getFakeType() {
		Random rand = new Random();
		List<String> typeList = new ArrayList<String>(Arrays.asList("String", "Integer", "Float", "Boolean"));
		return typeList.get(rand.nextInt(typeList.size()));
		
	}
	
}
