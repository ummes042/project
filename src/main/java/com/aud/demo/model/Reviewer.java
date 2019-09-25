package com.aud.demo.model;

import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reviewer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String fname;
	String lname;
	String department;
	String qualification;
	String Institute;
	String address;
	String email;
	String contact;
	String research_interests;
	String cvfile;
	String prooffile;
	String password;
	int active;
	int otp;
	boolean verified;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getInstitute() {
		return Institute;
	}
	public void setInstitute(String institute) {
		Institute = institute;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getResearch_interests() {
		return research_interests;
	}
	public void setResearch_interests(String research_interests) {
		this.research_interests = research_interests;
	}
	public String getCvfile() {
		return cvfile;
	}
	public void setCvfile(String cvfile) {
		this.cvfile = cvfile;
	}
	public String getProoffile() {
		return prooffile;
	}
	public void setProoffile(String prooffile) {
		this.prooffile = prooffile;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Reviewer(Long id, String fname, String lname, String department,String qualification ,String Institute, String address,
			String email, String contact, String research_interests, String cvfile, String prooffile, int active,
			int otp, boolean verified) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.department = department;
		this.qualification = qualification;
		this.Institute = Institute;
		this.address = address;
		this.email = email;
		this.contact = contact;
		this.research_interests = research_interests;
		this.cvfile = cvfile;
		this.prooffile = prooffile;
		this.active = active;
		this.otp = otp;
		this.verified = verified;
	}
	public Reviewer(String fname, String lname,String qualification ,String department, String Institute, String address, String email,
			String contact, String research_interests, String cvfile, String prooffile, int active, int otp,
			boolean verified) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.department = department;
		this.qualification = qualification;
		this.Institute = Institute;
		this.address = address;
		this.email = email;
		this.contact = contact;
		this.research_interests = research_interests;
		this.cvfile = cvfile;
		this.prooffile = prooffile;
		this.active = active;
		this.otp = otp;
		this.verified = verified;
	}
	public Reviewer() {
		super();
	}
	@Override
	public String toString() {
		return "Reviewer [id=" + id + ", fname=" + fname + ", lname=" + lname + ", department=" + department
				+ ", qualification=" + qualification + ", Institute=" + Institute + ", address=" + address
				+ ", email=" + email + ", contact=" + contact + ", research_interests=" + research_interests
				+ ", cvfile=" + cvfile + ", prooffile=" + prooffile + ", password=" + password + ", active=" + active
				+ ", otp=" + otp + ", verified=" + verified + "]";
	}
	
	
	
	
}
