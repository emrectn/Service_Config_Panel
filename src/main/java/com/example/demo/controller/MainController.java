package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	public static final Logger logger = LoggerFactory.getLogger(CostTypeController.class);
	
	@RequestMapping("/")
	public ModelAndView index(Authentication authentication) {
		logger.error("GetAut : "+authentication.getName());
		logger.error("GetRole : "+authentication.getAuthorities());
		ModelAndView model = new ModelAndView("index");
		return model;
	}

	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView model = new ModelAndView("register");
		model.addObject("msg", "oldu mpu kardeş?");
		return model;
	}

	@RequestMapping("/search")
	public String search() {
		return "search";
	}

}
