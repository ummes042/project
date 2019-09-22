package com.aud.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewerController {

	
	@RequestMapping(value="/reviewer" ,method = RequestMethod.GET)
	public String index() {
		return "reviewerhome";
	}
}
