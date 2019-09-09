package com.aud.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Paper {
	
	@Id
	@GeneratedValue
	
	long id;
	String title;
	String keywords;
	
	String Filename;
	String Comments;
	String experts;
	String auothers_name;
	String paper_status;
	
 enum Category{}

}
