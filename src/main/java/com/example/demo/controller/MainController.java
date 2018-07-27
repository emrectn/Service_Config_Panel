package com.example.demo.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Register;
import com.example.demo.model.User;
import com.example.demo.model.UserTeam;
import com.example.demo.service.CostTypeService;
import com.example.demo.service.PermTypeService;
import com.example.demo.service.RegisterService;
import com.example.demo.service.StatusTypeService;
import com.example.demo.service.UserService;
import com.example.demo.service.UserTeamService;

@Controller
public class MainController {
	
	public static final Logger logger = LoggerFactory.getLogger(CostTypeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegisterService registerService;

	@Autowired
	private UserTeamService userTeamService;

	@Autowired
	private CostTypeService costTypeService;

	@Autowired
	private PermTypeService permTypeService;
	
	@Autowired
	private StatusTypeService statusTypeService;
	
	@RequestMapping("/")
	public ModelAndView index(Authentication authentication) {
		logger.error("GetAut : "+authentication.getName());
		logger.error("GetRole : "+authentication.getAuthorities());
		ModelAndView model = new ModelAndView("index");
		model.addObject("name", authentication.getName());
		model.addObject("registers", registerService.findByTeamId(userService.findUserByEmail(authentication.getName())).stream().sorted(Comparator.comparing(Register::getId).reversed()).collect(Collectors.toList()));
		return model;
	}

	
	@RequestMapping("/configure")
	public ModelAndView configure(Authentication authentication) {
		ModelAndView model = new ModelAndView("configure");
		
		model.addObject("name", authentication.getName());
		model.addObject("userteams", userTeamService.findAll());
		model.addObject("costtypes", costTypeService.findAll());
		model.addObject("permtypes", permTypeService.findAll());
		model.addObject("status", statusTypeService.findAll());
		return model;
	}
	

}
