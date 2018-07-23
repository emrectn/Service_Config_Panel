package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.exception.CustomErrorType;
import com.example.demo.model.PermType;
import com.example.demo.model.Register;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RegisterService;
import com.example.demo.service.UserService;

@RestController
public class RegisterController {

	public static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userrepo; 
	
	@GetMapping("/app/api/register/{id}")
	public ResponseEntity<?> getRegister(@PathVariable Integer id) {
		logger.info("Fetching Register with id {}", id);
		Register register = registerService.findById(id);
		try {
			System.out.println(register);
		} catch (EntityNotFoundException e) {
			logger.error("Register with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Register with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Register>(register, HttpStatus.OK);
	}

	@GetMapping("/app/api/register")
	public ResponseEntity<List<Register>> getAllRegister() {
		List<Register> registers = registerService.findAll();
		if (registers.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Register>>(registers, HttpStatus.OK);
	}
	
	@PostMapping("/app/api/register")
	public ModelAndView createRegister(@NotNull Register register, BindingResult bindingResult, Authentication  authentication) {
		
		register.setCreater((User)userService.findUserByEmail(authentication.getName()));
		register.setUpdater((User)userService.findUserByEmail(authentication.getName()));
		register.setStartdate(getDate());
		logger.info("Creating Register : {}", register);
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		
//		if (bindingResult.hasErrors()) {
//			modelAndView.setViewName("index");
//			logger.debug("Kayıt hatası");
//		} else {
			registerService.save(register);
			modelAndView.addObject("successMessage", "User has been registered succesfully");
		
//		}
		return modelAndView;
	}
	
	@DeleteMapping("/app/api/register/{id}")
	public ResponseEntity<?> deleteRegister(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Register with id {}", id);
		
		Register register = registerService.findById(id);
		if (register == null) {
			logger.error("Unable to delete. PermType with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. PermType with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
		}
		
		registerService.deleteById(id);
		return new ResponseEntity<PermType>(HttpStatus.NO_CONTENT);
	}
	
	
	public Date getDate() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(ft.format(dNow));
		return dNow;
	}
	
	

}
