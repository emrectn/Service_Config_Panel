package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.exception.CustomErrorType;
import com.example.demo.model.CostType;
import com.example.demo.service.CostTypeService;

import javassist.NotFoundException;

@RestController
public class CostTypeController {
	
	public static final Logger logger = LoggerFactory.getLogger(CostTypeController.class);
	
	@Autowired
	private CostTypeService costTypeService;
	
	@GetMapping("/app/api/costtype/{id}")
	public ResponseEntity<?> getCostType(@PathVariable Integer id) {
		logger.info("Fetching CostType with id {}", id);
		CostType costType = costTypeService.findById(id);
		try {
			System.out.println(costType);
		} catch (EntityNotFoundException e) {
			logger.error("CostType with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("CostType with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CostType>(costType, HttpStatus.OK);
	}

	@GetMapping("/app/api/costtype")
	public ResponseEntity<List<CostType>> getAllCostType() {
		List<CostType> costTypes = costTypeService.findAll();
		if (costTypes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CostType>>(costTypes, HttpStatus.OK);
	}
	
	@PostMapping("/app/api/costtype")
	public ResponseEntity<?> createCostType(@RequestBody CostType costType, UriComponentsBuilder ucBuilder) {
		logger.info("Creating CostType : {}", costType);
		
		if(costType.getId() != null) {
			if(costTypeService.findById(costType.getId()) != null) {
				logger.error(""+costTypeService.findById(costType.getId()));
				logger.error("Unable to create. A CostType with id {} aldready exist", costType.getId());
				return new ResponseEntity(new CustomErrorType("Unable to create. A CostType with id " + 
			            costType.getId() + " already exist."),HttpStatus.CONFLICT);
			}
		}

		if(!costType.getCostname().isEmpty() && costTypeService.isCostTypeExist(costType)) {
			logger.error("Unable to create. A CostType with name {} aldready exist or null", costType.getCostname());
			return new ResponseEntity(new CustomErrorType("Unable to create. A CostType with name " + 
		            costType.getCostname() + " already exist."),HttpStatus.CONFLICT);
		}
		
		costTypeService.save(costType);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/app/api/costtype/{id}").buildAndExpand(costType.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/app/api/costtype/{id}")
	public ResponseEntity<?> deleteCostType(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting CostType with id {}", id);
		
		CostType costType = costTypeService.findById(id);
		if (costType == null) {
			logger.error("Unable to delete. CostType with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. CostType with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		costTypeService.deleteById(id);
		return new ResponseEntity<CostType>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/app/api/costtype")
	    public ResponseEntity<CostType> deleteAllUsers() {
	        logger.info("Deleting All Users");
	 
	        costTypeService.deleteAllUsers();
	        return new ResponseEntity<CostType>(HttpStatus.NO_CONTENT);
	    }


	
}
