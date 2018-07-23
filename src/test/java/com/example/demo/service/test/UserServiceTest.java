package com.example.demo.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.controller.StatusTypeController;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import com.fasterxml.jackson.datatype.jdk8.OptionalDoubleSerializer;


@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	private User response;
	
	private Optional<User> optionalResponse;
	
	private Role responseRole;
	
	private List<User> responseUserList;
	
	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	public static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Before
	public void beforeTest() {
		response = new User();
		responseUserList = new ArrayList<>();
		when(userRepository.findByEmailAndPassword("test@test.com", "test")).thenReturn(response);
		when(userRepository.findByEmail("test@test.com")).thenReturn(response);
		when(userRepository.findAll()).thenReturn(responseUserList);
		when(userRepository.findById(27)).thenReturn(optionalResponse);
		when(roleRepository.findByRole("Test")).thenReturn(responseRole);
		when(userRepository.save(response)).thenReturn(response);
		
	}
	
	@Test
	public void testUserByEmailAndPassword_valid_returnUser() {
		User user = userService.findUserByEmailAndPassword("test@test.com", "test");
		assertNotNull(user);
	}
	
	@Test
	public void testUserByEmailAndPassword_invalid_returnUser() {
		User user = userService.findUserByEmailAndPassword("GECERSİZt@test.com", "GECERSİZ");
		assertNull(user);
	}
	
	@Test
	public void testFindUserByEmail_validEmail_returnUser() {
		User user = userService.findUserByEmail("test@test.com");
		assertNotNull(user);
	}
	
	@Test
	public void testFindAll_returnUserList() {
		List<User> user_list = userService.findAll();
		assertNotNull(user_list);
	}
	
	@Test
	public void testFindUserById_validId_returnUser() {
		User user = userService.findUserById(27);
		logger.debug("User: " + user);
		assertNotNull(user);
	}
	
	@Test
	public void testSaveUser() {
		User user = new User();
		userService.saveUser(user);
	}
	
	@Test
	public void testLoadUserByUsername_valid_returnUserDetails() {
		assertNull(userService.loadUserByUsername("test", "test"));
	}
}
