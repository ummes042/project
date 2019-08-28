package com.aud.demo.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewPageController {

	@RequestMapping(value="/admin/author" ,method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	
	@RequestMapping(value="/reviewer" ,method = RequestMethod.GET)
	public String pageshow() {
		return "reviewer";
	}
	
	@RequestMapping(value="/fileupload" ,method = RequestMethod.GET)
	public String fileUpload() {
		return "file";
	}
}

