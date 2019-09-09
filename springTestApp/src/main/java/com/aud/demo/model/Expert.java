package com.aud.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Expert {
	@Id
	@GeneratedValue

	Long id;
	String name;
	String email;
	String contact;
	
	public Expert(Long id, String name, String email, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Expert(String name, String email, String contact) {
		super();
		
		this.name = name;
		this.email = email;
		this.contact = contact;
	}
	public Expert() {
		
	}
	
}
