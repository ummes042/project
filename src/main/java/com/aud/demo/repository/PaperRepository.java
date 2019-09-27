
package com.aud.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aud.demo.model.Paper;
import com.aud.demo.model.User;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {
	
	public List<Paper> findPaperByAuthor(User author);
	

  
}
