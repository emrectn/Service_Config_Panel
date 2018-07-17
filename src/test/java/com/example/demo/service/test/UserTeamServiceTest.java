package com.example.demo.service.test;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.controller.UserTeamController;
import com.example.demo.model.UserTeam;
import com.example.demo.repository.UserTeamRepository;
import com.example.demo.service.UserTeamService;
import com.example.demo.service.impl.UserTeamServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserTeamServiceTest {
	
	@Mock
	private UserTeamRepository userTeamRepository;
	
	
	@InjectMocks
	private UserTeamService userTeamService = new UserTeamServiceImpl();
	
	private UserTeam response;
	
	private List<UserTeam> response_list;

	public static final Logger logger = LoggerFactory.getLogger(UserTeamController.class);
	
	@Before
	public void beforeTest() {
		response = new UserTeam("TEST");
		response_list = new ArrayList<>();
		response_list.add(response);
		
		when(userTeamRepository.findByTeamname("TEST")).thenReturn(response);
		when(userTeamRepository.findAll()).thenReturn(response_list);
		when(userTeamRepository.getOne(2000)).thenReturn(response);
		when(userTeamRepository.save(response)).thenReturn(response);
		doNothing().when(userTeamRepository).deleteById(2000);
		doNothing().when(userTeamRepository).deleteAll();
	}
	
	@Test
	public void testIsUserTeamExists_validUserTeam_returnsTrue() {
		
		boolean isParamExits = userTeamService.isUserTeamExist(response);
		
		assertTrue(isParamExits);
	}
	
	@Test
	public void testIsUserTeamExists_invalidUserTeam_returnsFalse() {
		
		boolean isParamExits = userTeamService.isUserTeamExist(new UserTeam("2TEST"));
		
		assertFalse(isParamExits);
	}
	
	@Test
	public void testFindAll_returnsAllRecords() {
		List<UserTeam> list_permtype = userTeamService.findAll();
		assertNotNull(list_permtype);
	}
	
	@Test
	public void testFindById_validId_returnUserTeam() {
//		Bu kullanım doğrumu
		assertNotNull(userTeamService.findById(2000));
		
	}
	
	@Test
	public void testFindById_invalidId_returnUserTeam() {
//		Bumu tercih ediliyor
		UserTeam return_response = userTeamService.findById(2016);
		assertNull(return_response);
		
	}
	
	@Test
	public void testSave_returnUserTeam() {
		UserTeam return_response = userTeamService.save(response);
		assertEquals(response, return_response);
	}
	
//	İsim olarak ne tercih ediliyor
	@Test
	public void testDeleteById() {
//		Assert kullanıcakmıyız
		userTeamService.deleteById(2000);
		
	}
	
	@Test
	public void testDeleteAll() {
		userTeamService.deleteAllUsers();
	}
	

}
