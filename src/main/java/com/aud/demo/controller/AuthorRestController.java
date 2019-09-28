
package com.aud.demo.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.Valid;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		Map<Object,Object> responce = new HashMap<Object,Object>();
		Map<Object,Object>  errors = new HashMap<Object,Object>();
		ObjectMapper mapper = new ObjectMapper();

		
		if (bindingResult.hasErrors()) {
			
			 logger.info("Author->{} Binding results {}",author,bindingResult.getAllErrors());
			
			responce.put("type", "error");
			
			
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
				    
				    errors.put(fieldName, errorMsg);
				    
				    
				}
			 responce.put("errors", errors);
			 try {
				return mapper.writeValueAsString(responce);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}else {
	    author.setPassword( new BCryptPasswordEncoder().encode(author.getPassword()));
	    
	    //set an otp
	   
		Random rnd = new Random();
		int otp = 100000 + rnd.nextInt(900000);
		author.setOtp(otp);
	    
		
		
	    Role author_role = new Role(2,"AUTHOR");
//	    Role admin_role = new Role(1,"ADMIN");
	    Set<Role> roles = new HashSet<>();
	    roles.add(author_role);
//	    roles.add(admin_role);
	    
	    
		Long id = authorService.saveAuthor(author);
		author.setRoles(roles);
		authorService.saveAuthor(author);
		author.setId(id);
		
		
		new Mail().sendMail(author);
		
		logger.info("Author -> {}",author.toString());
		responce.put("type", "success");
			responce.put("obj", author);
			try {
				return mapper.writeValueAsString(responce);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return errorMsg;	
}
	
	
	@DeleteMapping("/delete/{authorId}")
	public String deleteAuthor(@PathVariable long authorId) {
		
		authorService.deleteById(authorId);
		logger.info("Authro has been deleted with id : {}",authorId);
//		String responce = "{type:'success',text:'Author has been deleted'}";
		Map<Object,Object> response = new HashMap<Object,Object>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.put("type","success");
		response.put("text","Author has been deleted");
		
		
		
		
		try {
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@GetMapping("/fetchAll")
	public List<User> getAllAuthors() {
		
		
		
		
		return authorService.findAll();
		
		
		
	}
	
}

