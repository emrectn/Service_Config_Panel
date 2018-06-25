package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.AppRestController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {
	
	@Autowired
	private AppRestController appRestController;
	
	@Test
	public void test_saveData() {
		appRestController.saveData();
		System.out.println(appRestController.findAll());
	}
}
