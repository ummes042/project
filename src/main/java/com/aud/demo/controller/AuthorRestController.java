package com.aud.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aud.demo.mail.Mail;
import com.aud.demo.model.Role;
import com.aud.demo.model.User;
import com.aud.demo.service.AuthorServiceImpl;






@RestController
@RequestMapping("/author/rest")
public class AuthorRestController {

	@Autowired
	AuthorServiceImpl authorService;
	
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/register")
	public String registerAuthor(@RequestBody @Valid User author, BindingResult bindingResult) {
		
		return saveOrUpdate(author, bindingResult);
		
		
	}
	
	
	@PutMapping("/register")
	public String updateAuthor(@RequestBody @Valid User author, BindingResult bindingResult) {
		
		
		return saveOrUpdate(author, bindingResult);
		
		
	}
	
	
	
public String saveOrUpdate(User author, BindingResult bindingResult) {
		
		String fieldName="";
		String errorMsg="";
		
		JSONObject responce = new JSONObject();
		JSONObject errors = new JSONObject();
		

		
		if (bindingResult.hasErrors()) {
			
			 logger.info("Author->{} Binding results {}",author,bindingResult.getAllErrors());
			
			try {
				responce.put("type", "error");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			 for (Object object : bindingResult.getAllErrors()) {
				 
				    if(object instanceof FieldError) {
				        FieldError fieldError = (FieldError) object;
				        fieldName = fieldError.getField()+"Err";
				        logger.info(" Binding Codes-> {}",fieldName);
				        
				    }

				    if(object instanceof ObjectError) {
				        ObjectError objectError = (ObjectError) object;
				        errorMsg = objectError.getDefaultMessage();
				        logger.info(" Binding Errors-> {} Message {}",objectError.getCode(),errorMsg);
				    }
				    
				    try {
						errors.put(fieldName, errorMsg);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
				    
				}
			 try {
				responce.put("errors", errors);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return responce.toString();
		}else {
	    author.setPassword( new BCryptPasswordEncoder().encode(author.getPassword()));
	    
	    //set an otp
	   
		Random rnd = new Random();
		int otp = 100000 + rnd.nextInt(900000);
		author.setOtp(otp);
	    
		
		
	    Role author_role = new Role(2,"AUTHOR");
	    Role admin_role = new Role(1,"ADMIN");
	    Set<Role> roles = new HashSet<>();
	    roles.add(author_role);
	    roles.add(admin_role);
	    
	    
		Long id = authorService.saveAuthor(author);
		author.setRoles(roles);
		authorService.saveAuthor(author);
		author.setId(id);
		
		
		new Mail().sendMail(author);
		
		logger.info("Author -> {}",author.toString());
		try {	
		responce.put("type", "success");
			responce.put("obj", author);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return responce.toString();
			
		}	
}
	
	
	@DeleteMapping("/delete/{authorId}")
	public String deleteAuthor(@PathVariable long authorId) {
		
		authorService.deleteById(authorId);
		logger.info("Authro has been deleted with id : {}",authorId);
//		String responce = "{type:'success',text:'Author has been deleted'}";
		JSONObject response = new JSONObject();
		try {
		response.put("type","success");
		response.put("text","Author has been deleted");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return response.toString();
		
	}
	
	@GetMapping("/fetchAll")
	public List<User> getAllAuthors() {
		
		
		
		
		return authorService.findAll();
		
		
		
	}
	
}
