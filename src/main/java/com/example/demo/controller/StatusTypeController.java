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
import com.example.demo.model.StatusType;
import com.example.demo.model.StatusType;
import com.example.demo.model.StatusType;
import com.example.demo.service.StatusTypeService;


@RestController
public class StatusTypeController {

	public static final Logger logger = LoggerFactory.getLogger(UserTeamController.class);
	
	@Autowired
	private StatusTypeService statusTypeService;
	
	@GetMapping("/app/api/statustype/{id}")
	public ResponseEntity<?> getStatusType(@PathVariable Integer id) {
		logger.info("Fetching StatusType with id {}", id);
		StatusType statusType = statusTypeService.findById(id);
		try {
			System.out.println(statusType);
		} catch (EntityNotFoundException e) {
			logger.error("StatusType with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("StatusType with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StatusType>(statusType, HttpStatus.OK);
	}
	
	@GetMapping("/app/api/statustype")
	public ResponseEntity<List<StatusType>> getAllStatusType() {
		List<StatusType> statusTypes = statusTypeService.findAll();
		if (statusTypes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<StatusType>>(statusTypes, HttpStatus.OK);
	}
	
	@PostMapping("/app/api/statustype")
	public ResponseEntity<?> createStatusType(@RequestBody StatusType statusType, UriComponentsBuilder ucBuilder) {
		logger.info("Creating StatusType : {}", statusType);
		
		if(statusType.getId() != null) {
			if(statusTypeService.findById(statusType.getId()) != null) {
				logger.error(""+statusTypeService.findById(statusType.getId()));
				logger.error("Unable to create. A StatusType with id {} aldready exist", statusType.getId());
				return new ResponseEntity(new CustomErrorType("Unable to create. A StatusType with id " + 
			            statusType.getId() + " already exist."),HttpStatus.CONFLICT);
			}
		}

		if(!statusType.getStatusname().isEmpty() && statusTypeService.isStatusTypeExist(statusType)) {
			logger.error("Unable to create. A StatusType with name {} aldready exist or null", statusType.getStatusname());
			return new ResponseEntity(new CustomErrorType("Unable to create. A StatusType with name " + 
		            statusType.getStatusname() + " already exist."),HttpStatus.CONFLICT);
		}
		
		statusTypeService.save(statusType);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/app/api/statustype/{id}").buildAndExpand(statusType.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/app/api/statustype/{id}")
	public ResponseEntity<?> deleteStatusType(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting StatusType with id {}", id);
		
		StatusType statusTypes = statusTypeService.findById(id);
		if (statusTypes == null) {
			logger.error("Unable to delete. StatusType with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. StatusType with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		statusTypeService.deleteById(id);
		return new ResponseEntity<StatusType>(HttpStatus.NO_CONTENT);
	}
	
	

}
