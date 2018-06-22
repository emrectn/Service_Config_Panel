package com.example.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import controller.AppRestController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {
	
	@Autowired
	@Qualifier("webController")
	AppRestController webController;
	
	@Test
	public void test_helloWorldReturnsValidResponse() {
		webController.saveData();
		assertTrue(!webController.findAll().isEmpty());
	}
}
