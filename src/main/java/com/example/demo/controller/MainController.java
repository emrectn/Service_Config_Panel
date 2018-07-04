package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/")
	public ModelAndView index() {
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
