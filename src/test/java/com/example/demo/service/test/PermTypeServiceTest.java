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

import com.example.demo.controller.PermTypeController;
import com.example.demo.model.PermType;
import com.example.demo.repository.PermTypeRepository;
import com.example.demo.service.PermTypeService;
import com.example.demo.service.impl.PermTypeServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PermTypeServiceTest {
	
	@Mock
	private PermTypeRepository permTypeRepository;
	
	
	@InjectMocks
	private PermTypeService permTypeService = new PermTypeServiceImpl();
	
	private PermType response;
	
	private List<PermType> response_list;

	public static final Logger logger = LoggerFactory.getLogger(PermTypeController.class);
	
	@Before
	public void beforeTest() {
		response = new PermType("TEST");
		response_list = new ArrayList<>();
		response_list.add(response);
		
		when(permTypeRepository.findByPermname("TEST")).thenReturn(response);
		when(permTypeRepository.findAll()).thenReturn(response_list);
		when(permTypeRepository.getOne(2000)).thenReturn(response);
		when(permTypeRepository.save(response)).thenReturn(response);
		doNothing().when(permTypeRepository).deleteById(2000);
		doNothing().when(permTypeRepository).deleteAll();
	}
	
	@Test
	public void testIsPermTypeExists_validPermType_returnsTrue() {
		
		boolean isParamExits = permTypeService.isPermTypeExist(response);
		
		assertTrue(isParamExits);
	}
	
	@Test
	public void testIsPermTypeExists_invalidPermType_returnsFalse() {
		
		boolean isParamExits = permTypeService.isPermTypeExist(new PermType("2TEST"));
		
		assertFalse(isParamExits);
	}
	
	@Test
	public void testFindAll_returnsAllRecords() {
		List<PermType> list_permtype = permTypeService.findAll();
		assertNotNull(list_permtype);
	}
	
	@Test
	public void testFindById_validId_returnPermType() {
//		Bu kullanım doğrumu
		assertNotNull(permTypeService.findById(2000));
		
	}
	
	@Test
	public void testFindById_invalidId_returnPermType() {
//		Bumu tercih ediliyor
		PermType return_response = permTypeService.findById(2016);
		assertNull(return_response);
		
	}
	
	@Test
	public void testSave_returnPermType() {
		PermType return_response = permTypeService.save(response);
		assertEquals(response, return_response);
	}
	
//	İsim olarak ne tercih ediliyor
	@Test
	public void testDeleteById() {
		permTypeService.deleteById(2000);
		
	}
	
	@Test
	public void testDeleteAll() {
		permTypeService.deleteAllUsers();
	}
	

}
