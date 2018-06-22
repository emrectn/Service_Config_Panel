package controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ConfigurationReader;
import com.example.demo.ServiceRepository;
import com.spring.model.ServiceConfig;

@RestController
public class AppRestController {
	
	@Autowired
	@Qualifier("serviceRepo")
	private ServiceRepository serviceRepo;
		
	@RequestMapping("/merhaba")
    public void merhaba(Model model) {
        String mesajicerigi="Merhaba Dünya";
        model.addAttribute("mesaj", mesajicerigi);
    }
	
	@RequestMapping("/add")
	public void addConfig() {
	}
	
	@RequestMapping("/get")
	public String getValue(@RequestParam("name") String name) {	
		
		if (!serviceRepo.findByName(name).isEmpty() && serviceRepo.findByName(name).get(0) != null)
			return serviceRepo.findByName(name).get(0).getValue();
		return "{'status':'error'}";
	}
		
	@RequestMapping("/save")
	public String saveData() {
		// save a single Service
		serviceRepo.save(new ServiceConfig("yeni", getFakeType(), "etiya.com", true, "applicationName"));
		
		// save a list of Services
		serviceRepo.save(new ServiceConfig("yeni1", getFakeType(), "5", true, "applicationName1"));
		serviceRepo.save(new ServiceConfig("yeni2", getFakeType(), "50", true, "applicationName2"));
		serviceRepo.save(new ServiceConfig("yeni3", getFakeType(), "1", true, "applicationName3"));
		serviceRepo.save(new ServiceConfig("yeni4", getFakeType(), "emre cetin", true, "applicationName4"));
		return "Done";
	}
	
	@RequestMapping("/findall")
	public Collection<ServiceConfig> findAll() {
		return serviceRepo.findAll();
	}
	
	@GetMapping("/delete")
	public String deleteConfig(@RequestParam("id") long id) {
		try {
			serviceRepo.deleteById(id);
			
		}catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return "Previously deleted";
		}
		return "Done";
			
	}
	
	@RequestMapping("/findbyname")
	public String fetchDataByLastName(@RequestParam("name") String name){
		StringBuilder bld = new StringBuilder();
		
		for(ServiceConfig s: serviceRepo.findByName(name)){
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
