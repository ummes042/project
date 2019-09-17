package com.aud.demo.model;

public enum PaperStatus {
  
	/*
	 * processing -> paper under review
	  if rejected -> request for changes
	   Accepted -> show payment options
	  paid -> waiting to get published
	  published -> certificate and paper are available for download
	*/ 
	 Processing, Accepted, Rejected, Paid, Published
	 
}
