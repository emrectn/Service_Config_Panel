package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.AppRestController;
import com.example.demo.model.CostType;
import com.example.demo.model.PermType;
import com.example.demo.model.StatusType;
import com.example.demo.model.User;
import com.example.demo.model.UserTeam;
import com.example.demo.repository.CostTypeRepository;
import com.example.demo.repository.PermTypeRepository;
import com.example.demo.repository.RegisterRepository;
import com.example.demo.repository.StatusTypeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserTeamRepository;
import com.example.demo.util.ConfigurationReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {
	
	@Autowired
	private AppRestController appRestController;
	
	@Autowired
	private ConfigurationReader configurationReader;
	
	@Autowired
	private CostTypeRepository costTypeRepo;
	
	@Autowired
	private PermTypeRepository permTypeRepo;
	
	@Autowired
	private UserTeamRepository userTeamRepo;
	
	@Autowired
	private StatusTypeRepository statusTypeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RegisterRepository registerRepo;
	

	@Test
	public void add_costType() {
		System.out.println("Test add_costType");
		CostType costType = new CostType("Deneme");
		
		// Unique kolon oldugu icin tekrar eklemeye izin vermez
		if (costTypeRepo.findByCostname(costType.getCostname()) != null) {
			System.out.println("Daha önceden eklemiş");
			
		// Yeni veri ekleme
		} else {
			System.out.println("Eklendi");
			costTypeRepo.save(costType);
		}
		assertNotNull(costTypeRepo.findAll().get(0));
		for (CostType c : costTypeRepo.findAll()) {
			System.out.println(c);
		}
	}
	
	@Test
	public void add_permType() {
		System.out.println("Test add_permType");
		PermType permType = new PermType("Perm1");
		
		// Unique kolon oldugu icin tekrar eklemeye izin vermez
		if (permTypeRepo.findByPermname(permType.getPermname()) != null) {
			System.out.println("Daha önceden eklenmiş PermType : " + permType.getPermname());
			
		// Yeni veri ekleme
		} else {
			System.out.println("PermType Eklendi : " + permType.getPermname());
			permTypeRepo.save(permType);
		}
		assertNotNull(permTypeRepo.findAll().get(0));
		for (PermType p : permTypeRepo.findAll()) {
			System.out.println(p);
		}
	}
	
	@Test
	public void add_userTeam() {
		System.out.println("Test add_userTeam");
		UserTeam userTeam = new UserTeam("User1");
		
		// Unique kolon oldugu icin tekrar eklemeye izin vermez
		if (userTeamRepo.findByTeamname(userTeam.getTeamname()) != null) {
			System.out.println("Daha önceden eklenmiş UserTeam : " + userTeam.getTeamname());
			
		// Yeni veri ekleme
		} else {
			System.out.println("UserTeam Eklendi : " + userTeam.getTeamname());
			userTeamRepo.save(userTeam);
		}
		assertNotNull(userTeamRepo.findAll().get(0));
		for (UserTeam u : userTeamRepo.findAll()) {
			System.out.println(u);
		}
	}
	
	@Test
	public void add_statusType() {
		System.out.println("Test add_statusType");
		StatusType statusType = new StatusType("User1");
		
		// Unique kolon oldugu icin tekrar eklemeye izin vermez
		if (statusTypeRepo.findByStatusname(statusType.getStatusname()) != null) {
			System.out.println("Daha önceden eklenmiş StatusType : " + statusType.getStatusname());
			
		// Yeni veri ekleme
		} else {
			System.out.println("StatusType Eklendi : " + statusType.getStatusname());
			statusTypeRepo.save(statusType);
		}
		assertNotNull(statusTypeRepo.findAll().get(0));
		for (StatusType u : statusTypeRepo.findAll()) {
			System.out.println(u);
		}
	}
	
	@Test
	public void add_user() {
		System.out.println("Test add_user");
		User user = new User();
		
	}
	
	
	
//	@Test
//	public void test_saveData() {
//		appRestController.saveData();
//		Integer value = configurationReader.<Integer>getValue("yeni");
//		if (value == null) {
//			System.out.println("Error do not cast");
//		} else {
//			System.out.println("The Value is : " + value);
//			assertEquals("etiya.com", value);
//		}
//	}
}
