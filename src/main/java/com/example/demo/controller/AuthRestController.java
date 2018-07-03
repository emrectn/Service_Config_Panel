package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

	public static final Logger logger = LoggerFactory.getLogger(CostTypeController.class);
	
	@GetMapping("/app/api/auth/login")
	public @ResponseBody String login(Authentication  authentication) {
		String msg = "";
		System.out.println("--" + authentication.getAuthorities());
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
}
