package com.aud.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.aud.demo.model.User;
import com.aud.demo.model.Paper;
import com.aud.demo.service.AuthorServiceImpl;
import com.aud.demo.service.PaperServiceImpl;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/paper")
public class PaperController {
	
	@Autowired
	PaperServiceImpl paperService;
	
	@Autowired
	AuthorServiceImpl authorService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/save")
	public String registerPaper(@RequestBody @Valid Paper paper, BindingResult bindingResult) {
		
		
		return saveOrUpdate(paper, bindingResult);
		
	}
	
	
	@PutMapping("/update")
	public String updatePaper(@RequestBody @Valid Paper paper, BindingResult bindingResult) {
		
		
		return saveOrUpdate(paper, bindingResult);
		
		
	}
	
	
	
public String saveOrUpdate(Paper paper, BindingResult bindingResult) {
		
		String fieldName="";
		String errorMsg="";
		
		JSONObject responce = new JSONObject();
		JSONObject errors = new JSONObject();
		
		
		
		if (bindingResult.hasErrors()) {
			
			 logger.info("Paper->{} Binding results {}",paper,bindingResult.getAllErrors());
			
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
			 return responce.toJSONString();
		}else {
	    //paper.setPassword( new BCryptPasswordEncoder().encode(paper.getPassword()));
		Long id = paperService.savePaper(paper);
		
		//paper.setId(id);
		
		logger.info("Paper -> {}",paper.toString());
			responce.put("type", "success");
			responce.put("obj", paper);
			return responce.toJSONString();
			
		}	
}
	
	
	@DeleteMapping("/delete/{paperdId}")
	public String deletePaper(@PathVariable long paperId) {
		
		paperService.deleteById(paperId);
		logger.info("Paper has been deleted with id : {}",paperId);
//		String responce = "{type:'success',text:'Author has been deleted'}";
		JSONObject response = new JSONObject();
		response.put("type","success");
		response.put("text","Paper has been deleted");
		
		
		
		
		return response.toString();
		
	}
	
	@GetMapping("/fetchAll")
	public List<Paper> getAllPapers() {
		
		
		
		
		return paperService.findAll();
		
		
		
	}
	
}

	

