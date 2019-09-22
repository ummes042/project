package com.aud.demo.service;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aud.demo.model.User;
import com.aud.demo.repository.ReviewerRepository;
import com.aud.demo.repository.RoleRepository;

@Service
public class ReviewerServiceImpl implements ReviewerService{
	
	@Autowired
	ReviewerRepository ReviewerRepo;
	
	@Autowired
	RoleRepository RoleRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public User findReviewerByEmail(String email) {
		// TODO Auto-generated method stub
		return ReviewerRepo.findReviewerByEmail(email) ;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return ReviewerRepo.findAll();
	}

	@Override
	public Long saveReviewer(User reviewer) {
		// TODO Auto-generated method stub
		ReviewerRepo.save(reviewer);
		logger.info("AuthorBeforeFlush -> {}",reviewer.getId());
		
		
		
		return reviewer.getId();

		
	}

		
	

	@Override
	public void updateReviewer(User Reviewer) {
		// TODO Auto-generated method stub
		ReviewerRepo.save(Reviewer);
		
	}

	@Override
	public User findReviewerById(long id) {
		// TODO Auto-generated method stub
		 Optional<User> OpReviewer = ReviewerRepo.findById(id);
		 if(OpReviewer.isPresent()) {
			return OpReviewer.get(); 
		 }else {
		 return null;
		 }
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		ReviewerRepo.deleteById(id);
		
	}
	
}
