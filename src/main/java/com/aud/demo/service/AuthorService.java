package com.aud.demo.service;

import java.util.List;

import com.aud.demo.model.User;

public interface AuthorService {

	public User findAuthorByEmail(String email);
	public List<User> findAll();
	public Long saveAuthor(User Author);
	public void updateAuthor(User Author);
	public User findAuthorById(long id);
	public void deleteById(long id);
//	public List<Author> findAuthorsByUsnIgnoreCaseContaining(String q);
		
}
