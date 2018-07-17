package com.example.demo.service.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.controller.CostTypeController;
import com.example.demo.model.CostType;
import com.example.demo.repository.CostTypeRepository;
import com.example.demo.service.CostTypeService;
import com.example.demo.service.impl.CostTypeServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CostTypeServiceTest {
	
	@Mock
	private CostTypeRepository costTypeRepository;
	
	
	@InjectMocks
	private CostTypeService costTypeService = new CostTypeServiceImpl();
	
	private CostType response;
	
	private List<CostType> response_list;

	public static final Logger logger = LoggerFactory.getLogger(CostTypeController.class);
	
	@Before
	public void beforeTest() {
		response = new CostType("TEST");
		response_list = new ArrayList<>();
		response_list.add(response);
		
		when(costTypeRepository.findByCostname("TEST")).thenReturn(response);
		when(costTypeRepository.findAll()).thenReturn(response_list);
		when(costTypeRepository.getOne(2000)).thenReturn(response);
		when(costTypeRepository.save(response)).thenReturn(response);
		doNothing().when(costTypeRepository).deleteById(2000);
		doNothing().when(costTypeRepository).deleteAll();
	}
	
	@Test
	public void testIsCostTypeExists_validCostType_returnsTrue() {
		
		boolean isParamExits = costTypeService.isCostTypeExist(response);
		
		assertTrue(isParamExits);
	}
	
	@Test
	public void testIsCostTypeExists_invalidCostType_returnsFalse() {
		
		boolean isParamExits = costTypeService.isCostTypeExist(new CostType("2TEST"));
		
		assertFalse(isParamExits);
	}
	
	@Test
	public void testFindAll_returnsAllRecords() {
		List<CostType> list_permtype = costTypeService.findAll();
		assertNotNull(list_permtype);
	}
	
	@Test
	public void testFindById_validId_returnCostType() {
//		Bu kullanım doğrumu
		assertNotNull(costTypeService.findById(2000));
		
	}
	
	@Test
	public void testFindById_invalidId_returnCostType() {
//		Bumu tercih ediliyor
		CostType return_response = costTypeService.findById(2016);
		assertNull(return_response);
		
	}
	
	@Test
	public void testSave_returnCostType() {
		CostType return_response = costTypeService.save(response);
		assertEquals(response, return_response);
	}
	
//	İsim olarak ne tercih ediliyor
	@Test
	public void testDeleteById() {
//		Assert kullanıcakmıyız
		costTypeService.deleteById(2000);
		
	}
	
	@Test
	public void testDeleteAll() {
		costTypeService.deleteAllUsers();
	}
	

}
