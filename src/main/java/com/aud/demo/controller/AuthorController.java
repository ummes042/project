package com.aud.demo.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorController {

	@RequestMapping(value="/admin/author" ,method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
