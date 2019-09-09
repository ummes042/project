package com.aud.demo.model;

import java.security.Timestamp;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Activity {
	
	@Id
	@GeneratedValue
	
	Long id;
	Date date;
    Timestamp time;
	Long rewiever_id;
	Long paper_id;
	String paper_status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Long getRewiever_id() {
		return rewiever_id;
	}
	public void setRewiever_id(Long rewiever_id) {
		this.rewiever_id = rewiever_id;
	}
	public Long getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(Long paper_id) {
		this.paper_id = paper_id;
	}
	public String getpaper_status() {
		return paper_status;
	}
	public void setpaper_status(String paper_status) {
		this.paper_status = paper_status;
	}

	
	public Activity(Long id, Date date, Timestamp time, Long rewiever_id, Long paper_id,String paper_status) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.rewiever_id = rewiever_id;
		this.paper_id = paper_id;
		this.paper_status = paper_status;
	}
	public Activity(Date date, Timestamp time, Long rewiever_id, Long paper_id,String paper_status) {
		super();
		
		this.date = date;
		this.time = time;
		this.rewiever_id = rewiever_id;
		this.paper_id = paper_id;
		this.paper_status = paper_status;
	}
	public Activity() {
		
	}


}
