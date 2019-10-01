package com.aud.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.util.logging.Logger;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.LoggerFactory;
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

import com.aud.demo.model.Paper;
import com.aud.demo.model.PaperStatus;
import com.aud.demo.model.User;
import com.aud.demo.repository.PaperRepository;
import com.aud.demo.service.AuthorServiceImpl;
import com.aud.demo.service.PaperServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/paper")
public class PaperController {
	
	@Autowired
	PaperServiceImpl paperService;
	
	@Autowired
	PaperRepository PaperRepo;
	
	
	
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
		
		Map responce = new HashMap<Object,Object>();
		Map errors = new HashMap<Object,Object>();
		 ObjectMapper mapper = new ObjectMapper();
		
		
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
			 try {
				return mapper.writeValueAsString(responce);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
	    
		paper.setStatus(PaperStatus.Processing);	
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User author = authorService.findAuthorByEmail(auth.getName());
		
		paper.setAuthor(author);
		
		Long id = paperService.savePaper(paper);
		
		//paper.setId(id);
		
		logger.info("Paper -> {}",paper.toString());
			responce.put("type", "success");
			responce.put("obj", paper);
			
			 try {
					return mapper.writeValueAsString(responce);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return errorMsg;	
}
	
	
	@DeleteMapping("/delete/{paperdId}")
	public String deletePaper(@PathVariable long paperId) {
		
		paperService.deleteById(paperId);
		logger.info("Paper has been deleted with id : {}",paperId);
//		String responce = "{type:'success',text:'Author has been deleted'}";
		Map response = new HashMap<Object,Object>();
		response.put("type","success");
		response.put("text","Paper has been deleted");
		
		
		
		return response.toString();
		
	}
	
	@GetMapping("/fetchAll")
	public List<Paper> getAllPapers() {
		
		
		
		
		return paperService.findAll();
		
		
		
	}
	
	@GetMapping("/fetch")
	public Set<Paper> getPapersOfAuthor() {
		
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User author = authorService.findAuthorByEmail(auth.getName());
	    return author.getPaper();
//		return paperService.findPapersById(author);
		
		
		
	}
	
	
	
	
	@GetMapping("/accept/{pid}")
	public String paperStatusAccept(@PathVariable long pid ) {
		 Paper paper = paper.findPaperById(pid);
		paper.setStatus(PaperStatus.Accepted);	
		PaperRepo.save(paper);
		return paperStatusAccept(pid);
	}
	

	
}

	

