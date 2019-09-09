package com.aud.demo.service;

import java.util.List;

import com.aud.demo.model.Paper;

public interface PaperService {
	
	
	public List<Paper> findAll();
	public Long savePaper(Paper Paper);
	public void updatePaper(Paper Paper);
	public Paper findPaperById(long id);
	public void deleteById(long id);
	public boolean updatePaperWithFilename(String filename,long pid);
	

}
