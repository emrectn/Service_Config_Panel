package com.example.demo.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomErrorType;
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
	
	
	

}
