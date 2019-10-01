package com.aud.demo.service;

import java.util.List;
import java.util.Optional;
//import java.util.logging.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aud.demo.model.User;
import com.aud.demo.repository.RoleRepository;
import com.aud.demo.repository.UserRepository;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	UserRepository ar;

	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public User findAuthorByEmail(String email) {
		// TODO Auto-generated method stub
		return ar.findUserByEmail(email);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return ar.findAll();
	}

	@Override
	public Long saveAuthor(User author) {
		// TODO Auto-generated method stub
		
		ar.save(author);
		logger.info("AuthorBeforeFlush -> {}",author.getId());
		
		
		
		return author.getId();

	}

	@Override
	public void updateAuthor(User author) {
		// TODO Auto-generated method stub
		
		
		ar.save(author);
		
		
		
	}

	@Override
	public User findAuthorById(long id) {
		// TODO Auto-generated method stub
		
		 Optional<User> OpAuthor = ar.findById(id);
		 if(OpAuthor.isPresent()) {
			return OpAuthor.get(); 
		 }else {
		 return null;
		 }
	}

	@Override
	public void deleteById(long id) {
		
		ar.deleteById(id);
		
		
	}
	
	

}