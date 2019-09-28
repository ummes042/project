package com.aud.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aud.demo.model.Categories;
import com.aud.demo.model.Paper;
import com.aud.demo.model.Reviewer;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer,Long> {
	
	
      public Reviewer findReviewerByEmail(String email);
      
      @Query(name="query_get_all_papers", value=" FROM Paper p where p.status= 'Processing' and p.category = :category")
      public List<Paper> fetchPapersForReviewer(@Param("category") Categories category);
  	
}
