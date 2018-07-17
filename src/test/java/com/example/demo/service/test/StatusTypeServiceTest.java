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

import com.example.demo.controller.StatusTypeController;
import com.example.demo.model.StatusType;
import com.example.demo.repository.StatusTypeRepository;
import com.example.demo.service.StatusTypeService;
import com.example.demo.service.impl.StatusTypeServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StatusTypeServiceTest {
	
	@Mock
	private StatusTypeRepository statusTypeRepository;
	
	
	@InjectMocks
	private StatusTypeService statusTypeService = new StatusTypeServiceImpl();
	
	private StatusType response;
	
	private List<StatusType> response_list;

	public static final Logger logger = LoggerFactory.getLogger(StatusTypeController.class);
	
	@Before
	public void beforeTest() {
		response = new StatusType("TEST");
		response_list = new ArrayList<>();
		response_list.add(response);
		
		when(statusTypeRepository.findByStatusname("TEST")).thenReturn(response);
		when(statusTypeRepository.findAll()).thenReturn(response_list);
		when(statusTypeRepository.getOne(2000)).thenReturn(response);
		when(statusTypeRepository.save(response)).thenReturn(response);
		doNothing().when(statusTypeRepository).deleteById(2000);
		doNothing().when(statusTypeRepository).deleteAll();
	}
	
	@Test
	public void testIsStatusTypeExists_validStatusType_returnsTrue() {
		
		boolean isParamExits = statusTypeService.isStatusTypeExist(response);
		
		assertTrue(isParamExits);
	}
	
	@Test
	public void testIsStatusTypeExists_invalidStatusType_returnsFalse() {
		
		boolean isParamExits = statusTypeService.isStatusTypeExist(new StatusType("2TEST"));
		
		assertFalse(isParamExits);
	}
	
	@Test
	public void testFindAll_returnsAllRecords() {
		List<StatusType> list_permtype = statusTypeService.findAll();
		assertNotNull(list_permtype);
	}
	
	@Test
	public void testFindById_validId_returnStatusType() {
//		Bu kullanım doğrumu
		assertNotNull(statusTypeService.findById(2000));
		
	}
	
	@Test
	public void testFindById_invalidId_returnStatusType() {
//		Bumu tercih ediliyor
		StatusType return_response = statusTypeService.findById(2016);
		assertNull(return_response);
		
	}
	
	@Test
	public void testSave_returnStatusType() {
		StatusType return_response = statusTypeService.save(response);
		assertEquals(response, return_response);
	}
	
//	İsim olarak ne tercih ediliyor
	@Test
	public void testDeleteById() {
//		Assert kullanıcakmıyız
		statusTypeService.deleteById(2000);
		
	}
	
	@Test
	public void testDeleteAll() {
		statusTypeService.deleteAllUsers();
	}
	

}
