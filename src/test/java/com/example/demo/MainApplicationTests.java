package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.AppRestController;
import com.example.demo.util.ConfigurationReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {
	
	@Autowired
	private AppRestController appRestController;
	
	@Autowired
	private ConfigurationReader configurationReader;
	
	@Test
	public void test_saveData() {
		appRestController.saveData();
		Integer value = configurationReader.<Integer>getValue("yeni");
		if (value == null) {
			System.out.println("Error do not cast");
		} else {
			System.out.println("The Value is : " + value);
			assertEquals("etiya.com", value);
		}

		
	}
}
