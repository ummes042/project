package com.aud.demo.service;

import java.util.List;

import com.aud.demo.model.Categories;
import com.aud.demo.model.Paper;
import com.aud.demo.model.Reviewer;


public interface ReviewerService {
	
	
		public Reviewer findReviewerByEmail(String email);
		public List<Reviewer> findAll();
		public Long saveReviewer(Reviewer reviewer);
		public void updateReviewer(Reviewer reviewer);
		public Reviewer findReviewerById(long id);
		public void deleteById(long id);
		public List<Paper> fetchPapersForReviewer(Categories category);
			
	}


