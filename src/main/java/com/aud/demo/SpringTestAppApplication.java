package com.aud.demo;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTestAppApplication{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	

	public static void main(String[] args) {
		SpringApplication.run(SpringTestAppApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//	Student s =	new Student("Sameer");
//		
//		studentRepo.save(s);
//		
//		logger.info("Student has been inserter {}",s);
//		
//	}

}
