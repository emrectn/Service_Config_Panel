package com.example.demo.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.exception.CustomErrorType;
import com.example.demo.model.PermType;
import com.example.demo.service.PermTypeService;

@RestController
public class PermTypeController {
	
	
	public static final Logger logger = LoggerFactory.getLogger(PermTypeController.class);
	
	@Autowired
	private PermTypeService permTypeService;
	
	
	@GetMapping("/app/api/permtype/{id}")
	public ResponseEntity<?> getPermType(@PathVariable Integer id) {
		logger.info("Fetching PermType with id {}", id);
		PermType permType = permTypeService.findById(id);
		try {
			System.out.println(permType);
		} catch (EntityNotFoundException e) {
			logger.error("PermType with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("PermType with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PermType>(permType, HttpStatus.OK);
	}
	@GetMapping("/app/api/permtype")
	public ResponseEntity<List<PermType>> getAllPermType() {
		List<PermType> permTypes = permTypeService.findAll();
		if (permTypes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PermType>>(permTypes, HttpStatus.OK);
	}
	
	@PostMapping("/app/api/permtype")
	public ResponseEntity<?> createPermType(@RequestBody PermType permType, UriComponentsBuilder ucBuilder) {
		logger.info("Creating PermType : {}", permType);
		
		if(permType.getId() != null) {
			if(permTypeService.findById(permType.getId()) != null) {
				logger.error(""+permTypeService.findById(permType.getId()));
				logger.error("Unable to create. A PermType with id {} aldready exist", permType.getId());
				return new ResponseEntity(new CustomErrorType("Unable to create. A PermType with id " + 
			            permType.getId() + " already exist."),HttpStatus.CONFLICT);
			}
		}

		if(!permType.getPermname().isEmpty() && permTypeService.isPermTypeExist(permType)) {
			logger.error("Unable to create. A PermType with name {} aldready exist or null", permType.getPermname());
			return new ResponseEntity(new CustomErrorType("Unable to create. A PermType with name " + 
		            permType.getPermname() + " already exist."),HttpStatus.CONFLICT);
		}
		
		permTypeService.save(permType);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/app/api/permtype/{id}").buildAndExpand(permType.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/app/api/permtype/{id}")
	public ResponseEntity<?> deletePermType(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting PermType with id {}", id);
		
		PermType permType = permTypeService.findById(id);
		if (permType == null) {
			logger.error("Unable to delete. PermType with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. PermType with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		permTypeService.deleteById(id);
		return new ResponseEntity<PermType>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/app/api/permtype")
	    public ResponseEntity<PermType> deleteAllUsers() {
	        logger.info("Deleting All Users");
	 
	        permTypeService.deleteAllUsers();
	        return new ResponseEntity<PermType>(HttpStatus.NO_CONTENT);
	    }



}
