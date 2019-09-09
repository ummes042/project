package com.aud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aud.demo.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

}
