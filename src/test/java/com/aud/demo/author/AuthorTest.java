package com.aud.demo.author;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.aud.demo.model.User;
import com.aud.demo.service.AuthorServiceImpl;


class AuthorTest {

	@Autowired
	AuthorServiceImpl authorService;
	
	@Test
	@DirtiesContext
	void saveAuthor() {
//		Author a = new Author( "fname","lname","email","mobile","password","line1","line2","city","state","country","pincode");
//		authorService.saveAuthor(a);
//		assertEquals(a.getId()>0, a.getId());
			
		
		
	}

}
