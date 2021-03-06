package com.example.demo.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserTeamService;


@RestController
public class AuthRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserTeamService userTeamService;

	public static final Logger logger = LoggerFactory.getLogger(CostTypeController.class);
	
	@GetMapping("/app/api/auth/login")
	public @ResponseBody String login(Authentication  authentication) {
		String msg = "";
		logger.error("GetAut : "+authentication.getAuthorities());
		
		for (GrantedAuthority authority : authentication.getAuthorities()) {
		     String role = authority.getAuthority();
                    msg += authentication.getName()+", You have "+ role;
		}
		return msg;
	}
	
	@GetMapping("/app/api/auth/logout")
	public String logout() {
		return "logout";
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@GetMapping("/registration")
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.addObject("userteams", userTeamService.findAll().stream().map(s-> s.getTeamname()).collect(Collectors.toList()));
		modelAndView.addObject("asd","helleo");
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@PostMapping("/app/api/auth/registration")
	public ModelAndView createNewUser(@NotNull User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("login");
			
		}
		return modelAndView;
	}
	
}
