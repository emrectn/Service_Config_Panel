package com.example.demo.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.exception.CustomErrorType;
import com.example.demo.model.CostType;
import com.example.demo.model.UserTeam;
import com.example.demo.service.UserTeamService;

@RestController
public class UserTeamController {
	
	
	public static final Logger logger = LoggerFactory.getLogger(UserTeamController.class);
	
	@Autowired
	private UserTeamService userTeamService;
	
	
	@GetMapping("/app/api/userteam/{id}")
	public ResponseEntity<?> getUserTeam(@PathVariable Integer id) {
		logger.info("Fetching UserTeam with id {}", id);
		UserTeam userTeam = userTeamService.findById(id);
		try {
			System.out.println(userTeam);
		} catch (EntityNotFoundException e) {
			logger.error("UserTeam with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("UserTeam with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserTeam>(userTeam, HttpStatus.OK);
	}

	@GetMapping("/app/api/userteam")
	public ResponseEntity<List<UserTeam>> getAllUserTeam() {
		List<UserTeam> userTeams = userTeamService.findAll();
		if (userTeams.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserTeam>>(userTeams, HttpStatus.OK);
	}
	
	@PostMapping("/app/api/userteam")
	public ResponseEntity<?> createUserTeam(@RequestBody UserTeam userTeam, UriComponentsBuilder ucBuilder) {
		logger.info("Creating UserTeam : {}", userTeam);
		
		if(userTeam.getId() != null) {
			if(userTeamService.findById(userTeam.getId()) != null) {
				logger.error(""+userTeamService.findById(userTeam.getId()));
				logger.error("Unable to create. A UserTeam with id {} aldready exist", userTeam.getId());
				return new ResponseEntity(new CustomErrorType("Unable to create. A UserTeam with id " + 
			            userTeam.getId() + " already exist."),HttpStatus.CONFLICT);
			}
		}

		if(!userTeam.getTeamname().isEmpty() && userTeamService.isUserTeamExist(userTeam)) {
			logger.error("Unable to create. A UserTeam with name {} aldready exist or null", userTeam.getTeamname());
			return new ResponseEntity(new CustomErrorType("Unable to create. A UserTeam with name " + 
		            userTeam.getTeamname() + " already exist."),HttpStatus.CONFLICT);
		}
		
		userTeamService.save(userTeam);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/app/api/userteam/{id}").buildAndExpand(userTeam.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/app/api/userteam/{id}")
	public ResponseEntity<?> deleteUserTeam(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting UserTeam with id {}", id);
		
		UserTeam userTeam = userTeamService.findById(id);
		if (userTeam == null) {
			logger.error("Unable to delete. UserTeam with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. UserTeam with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		userTeamService.deleteById(id);
		return new ResponseEntity<UserTeam>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/app/api/userteam")
	    public ResponseEntity<UserTeam> deleteAllUsers() {
	        logger.info("Deleting All Users");
	 
	        userTeamService.deleteAllUsers();
	        return new ResponseEntity<UserTeam>(HttpStatus.NO_CONTENT);
	    }



}
