package com.aud.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.management.Query;
import javax.persistence.EntityManager;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aud.demo.model.Categories;
import com.aud.demo.model.Paper;
import com.aud.demo.model.Reviewer;
import com.aud.demo.repository.ReviewerRepository;
import com.aud.demo.repository.RoleRepository;

@Service
public class ReviewerServiceImpl implements ReviewerService{
	
	@Autowired
	ReviewerRepository ReviewerRepo;
	
	@Autowired
	RoleRepository RoleRepo;
	
	@Autowired
	EntityManager em;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Reviewer findReviewerByEmail(String email) {
		// TODO Auto-generated method stub
		return ReviewerRepo.findReviewerByEmail(email);
	}

	@Override
	public List<Reviewer> findAll() {
		// TODO Auto-generated method stub
		return ReviewerRepo.findAll();
	}

	@Override
	public Long saveReviewer(Reviewer reviewer) {
		// TODO Auto-generated method stub
		ReviewerRepo.save(reviewer);
		logger.info("AuthorBeforeFlush -> {}",reviewer.getId());
		
		
		
		return reviewer.getId();
	}
	@Override
	public void updateReviewer(Reviewer reviewer) {
		// TODO Auto-generated method stub
		ReviewerRepo.save(reviewer);
	}

	@Override
	public Reviewer findReviewerById(long id) {
		// TODO Auto-generated method stub
		Optional<Reviewer> OpReviewer = ReviewerRepo.findById(id);
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

	@Override
	public List<Paper> fetchPapersForReviewer(Categories category) {
		
		// Using @query and JPQL
//		return ReviewerRepo.fetchPapersForReviewer(category);
		String sqlString =  "Select * FROM Paper p where p.status= 'Processing' and p.category = :category";
		Query q = em.createNativeQuery(sqlString,Paper.class);
		logger.info("Category is :{}",category);
		String scategory = category.toString();
		q.setParameter("category", scategory);
		return q.getResultList();
		
	}

	
	
}


