package com.sprhib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkController {
	
	@RequestMapping(value="/")
	public ModelAndView mainPage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("page", "home");
		return modelAndView;
	}
	
	@RequestMapping(value="/index")
	public ModelAndView indexPage() {
		return mainPage();
	}
}
