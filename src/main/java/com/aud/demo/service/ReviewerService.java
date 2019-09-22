package com.aud.demo.service;

import java.util.List;

import com.aud.demo.model.User;

public interface ReviewerService {
	
	
		public User findReviewerByEmail(String email);
		public List<User> findAll();
		public Long saveReviewer(User reviewer);
		public void updateReviewer(User reviewer);
		public User findReviewerById(long id);
		public void deleteById(long id);
		
			
	}


