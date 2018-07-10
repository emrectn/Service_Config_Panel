package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.RegisterService;
import com.example.demo.service.UserService;

@Controller
public class MainController {
	
	public static final Logger logger = LoggerFactory.getLogger(CostTypeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/")
	public ModelAndView index(Authentication authentication) {
		logger.error("GetAut : "+authentication.getName());
		logger.error("GetRole : "+authentication.getAuthorities());
		logger.error("RegisterList : " + registerService.findByTeamId(userService.findUserByEmail(authentication.getName())));
		ModelAndView model = new ModelAndView("index");
		model.addObject("name", authentication.getName());
		model.addObject("registers", registerService.findByTeamId(userService.findUserByEmail(authentication.getName())));
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
	
	@RequestMapping("/admin")
	public String admin() {
		return "admün";
	}

}
