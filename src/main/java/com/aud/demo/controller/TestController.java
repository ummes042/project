package com.aud.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aud.demo.model.User;

@RestController
public class TestController {

	@GetMapping("/test2")
	public User test(HttpSession session) {
	
		return (User)session.getAttribute("author");
		
	}
}
