package com.aud.demo.controller;

//import java.util.HashSet;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//import javax.validation.Valid;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.aud.demo.mail.Mail;
//import com.aud.demo.model.Role;
//import com.aud.demo.model.User;
//import com.aud.demo.service.ReviewerServiceImpl;
//
//import net.minidev.json.JSONObject;
//
//
//@RestController
//@RequestMapping("/reviewer")
//public class ReviewerRestController {
//	
//	@Autowired
//	ReviewerServiceImpl reviewerService;
//	
//	
//	
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	@PostMapping("/reviewer/register")
//	public String registerReviewer(@RequestBody @Valid User reviewer, BindingResult bindingResult) {
//		
//		return saveOrUpdate(reviewer, bindingResult);
//		
//		
//	}
//	
//	
//	@PutMapping("/reviewer/register")
//	public String updateReviewer(@RequestBody @Valid User reviewer, BindingResult bindingResult) {
//		
//		
//		return saveOrUpdate(reviewer, bindingResult);
//		
//		
//	}
//	
//	
//	
//public String saveOrUpdate(User reviewer, BindingResult bindingResult) {
//		
//		String fieldName="";
//		String errorMsg="";
//		
//		JSONObject responce = new JSONObject();
//		JSONObject errors = new JSONObject();
//		
//
//		
//		if (bindingResult.hasErrors()) {
//			
//			 logger.info("Reviewer->{} Binding results {}",reviewer,bindingResult.getAllErrors());
//			
//			 responce.put("type", "error");
//			
//			
//			 for (Object object : bindingResult.getAllErrors()) {
//				 
//				    if(object instanceof FieldError) {
//				        FieldError fieldError = (FieldError) object;
//				        fieldName = fieldError.getField()+"Err";
//				        logger.info(" Binding Codes-> {}",fieldName);
//				        
//				    }
//
//				    if(object instanceof ObjectError) {
//				        ObjectError objectError = (ObjectError) object;
//				        errorMsg = objectError.getDefaultMessage();
//				        logger.info(" Binding Errors-> {} Message {}",objectError.getCode(),errorMsg);
//				    }
//				    errors.put(fieldName, errorMsg);
//				    
//				    
//				}
//			 responce.put("errors", errors);
//			 return responce.toJSONString();
//		}else {
//			reviewer.setPassword( new BCryptPasswordEncoder().encode(reviewer.getPassword()));
//	    
//	    //set an otp
//	   
//		Random rnd = new Random();
//		int otp = 100000 + rnd.nextInt(900000);
//		reviewer.setOtp(otp);
//	    
//		
//		
//	    //Role author_role = new Role(2,"AUTHOR");
//	    //Role admin_role = new Role(1,"ADMIN");
//	    Role reviewer_role = new Role(3,"REVIEWER");
//	    Set<Role> roles = new HashSet<>();
//	    //roles.add(author_role);
//	    roles.add(reviewer_role);
//	    
//	    
//		Long id = reviewerService.saveReviewer(reviewer);
//		reviewer.setRoles(roles);
//		reviewerService.saveReviewer(reviewer);
//		reviewer.setId(id);
//		
//		
//		new Mail().sendMail(reviewer);
//		
//		logger.info("reviewer -> {}",reviewer.toString());
//			responce.put("type", "success");
//			responce.put("obj", reviewer);
//			return responce.toJSONString();
//			
//		}	
//}
//	
//	
//	@DeleteMapping("/delete/{reviewerId}")
//	public String deleteReviewer(@PathVariable long reviewerId) {
//		
//		reviewerService.deleteById(reviewerId);
//		logger.info("Authro has been deleted with id : {}",reviewerId);
////		String responce = "{type:'success',text:'Author has been deleted'}";
//		JSONObject response = new JSONObject();
//		response.put("type","success");
//		response.put("text","reviewer has been deleted");
//		
//		
//		
//		
//		return response.toString();
//		
//	}
//	
//	@GetMapping("/fetchAll")
//	public List<User> getAllReviewers() {
//		
//		
//		
//		
//		return reviewerService.findAll();
//		
//		
//		
//	}
//	
//}

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.validation.Valid;

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
import com.aud.demo.model.Paper;
import com.aud.demo.model.Role;
import com.aud.demo.model.User;
import com.aud.demo.service.AuthorServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/reviewer/rest")
public class ReviewerRestController {

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
	    
		
		
	    Role reviewer_role = new Role(3,"REVIEWER");
//	    Role admin_role = new Role(1,"ADMIN");
	    Set<Role> roles = new HashSet<>();
	    roles.add(reviewer_role);
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
		response.put("text","Reviewer has been deleted");
		
		
		
		
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




