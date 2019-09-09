package com.aud.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aud.demo.model.User;
import com.aud.demo.model.Paper;
import com.aud.demo.repository.PaperRepository;
import com.aud.demo.repository.RoleRepository;

@Service
public class PaperServiceImpl implements PaperService {

	@Autowired
	PaperRepository PaperRepo;

	@Autowired
	AuthorServiceImpl authorService;
    
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public List<Paper> findAll() {
		// TODO Auto-generated method stub
		return PaperRepo.findAll();
	}

	@Override
	public Long savePaper(Paper paper) {
		// TODO Auto-generated method stub
		
		PaperRepo.save(paper);
		logger.info("paperBeforeFlush -> {}",paper.getId());
		
		
		
		return paper.getId();

	}

	@Override
	public void updatePaper(Paper paper) {
		// TODO Auto-generated method stub
		
		
		PaperRepo.save(paper);
		
		
		
	}

	@Override
	public Paper findPaperById(long id) {
		// TODO Auto-generated method stub
		
		 Optional<Paper> OpPaper = PaperRepo.findById(id);
		 if(OpPaper.isPresent()) {
			return OpPaper.get(); 
		 }else {
		 return null;
		 }
	}

	@Override
	public void deleteById(long id) {
		
		PaperRepo.deleteById(id);
		
		
	}

	@Override
	public boolean updatePaperWithFilename(String filename, long pid) {
		
		 // fetch paper using pid
		 Paper paper = this.findPaperById(pid);
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //check authentication
			User author = authorService.findAuthorByEmail(auth.getName()); //checking through email from db
			
			
        if(paper==null) {
        	return false;
        }
        //update paper with filename
       // paper.setAuthor(author);
		 paper.setFilename(filename);
		 PaperRepo.save(paper);
		 
		
		return true;
	}
	
	

}


	