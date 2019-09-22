package com.aud.demo.model;

import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Student {

	
	@Id
	int id;
	String name;
}
