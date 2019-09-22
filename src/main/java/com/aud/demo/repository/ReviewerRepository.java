package com.aud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aud.demo.model.User;

@Repository
public interface ReviewerRepository extends JpaRepository<User,Long> {
	
      public User findReviewerByEmail(String email);
}
