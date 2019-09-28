package com.aud.demo.author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

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
