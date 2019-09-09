package com.aud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aud.demo.model.Paper;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {

}
