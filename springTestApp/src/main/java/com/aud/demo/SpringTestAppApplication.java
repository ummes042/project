 package com.aud.demo;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aud.demo.model.Student;
import com.aud.demo.repository.StudentRepo;

@SpringBootApplication
public class SpringTestAppApplication implements CommandLineRunner{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepo studentRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringTestAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	Student s =	new Student("Sameer");
		
		studentRepo.save(s);
		
		logger.info("Student has been inserter {}",s);
		
	}

}
