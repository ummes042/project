package com.aud.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AuthorController {

	@RequestMapping(value="/author" ,method = RequestMethod.GET)
	public String index() {
		return "authorhome";
	}
	
	@RequestMapping(value="/author/submit" ,method = RequestMethod.GET)
	public String submitpaper() {
		return "submitpaper";
	}

}
