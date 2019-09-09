package com.aud.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Admin {
	
	@Id
	@GeneratedValue
	
	long id;
	String name;
	String email;
	String Password;

	
	public Admin(long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		Password = password;
	}
	public Admin(String name, String email, String password) {
		super();
		
		this.name = name;
		this.email = email;
		Password = password;
	}
	public Admin() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	

}
