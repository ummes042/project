package com.aud.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Author {
	
	@Id
	@GeneratedValue
	
	Long id;
	String fname;
	String lname;
	String email;
	
	public Author(Long id, String fname, String lname, String email) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	public Author(String fname, String lname, String email) {
		super();
		
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	public Long getid() {
		return id;
	}
	public void setid(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Author() {
		
	}
	

}
