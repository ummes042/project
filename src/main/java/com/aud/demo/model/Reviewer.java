package com.aud.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class Reviewer extends User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	
	

//	String fname;
//	String lname;
	String department;
	String qualification;
//	String Institute;
//	String address;
//	String email;
//	String contact;
	@Enumerated(EnumType.STRING)
    Categories research_interests;
	String cvfile;
	String prooffile;
//	String password;
	
//	int active;
//	int otp;
//	boolean verified;
	
	
	// list of categories to be assigned by admin
	//List<Categories> allowedCategories = new ArrayList<Categories>();
	
	

//	
//	@ManyToMany
//	@JoinTable(name = "reviewer_paper", joinColumns = @JoinColumn(name = "reviewer_id"), inverseJoinColumns = @JoinColumn(name = "paper_id"))
//	Set<Paper> papers;

	
	public Long getId() {
		return id;
	}

//	public String getFname() {
//		return fname;
//	}
//	public void setFname(String fname) {
//		this.fname = fname;
//	}
//	public String getLname() {
//		return lname;
//	}
//	public void setLname(String lname) {
//		this.lname = lname;
//	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
//	public String getInstitute() {
//		return Institute;
//	}
//	public void setInstitute(String institute) {
//		Institute = institute;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getContact() {
//		return contact;
//	}
//	public void setContact(String contact) {
//		this.contact = contact;
//	}
	
	
	
	public String getCvfile() {
		return cvfile;
	}
	public Categories getResearch_interests() {
		return research_interests;
	}

	public void setResearch_interests(Categories research_interests) {
		this.research_interests = research_interests;
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
	
//	public int getActive() {
//		return active;
//	}
//	public void setActive(int active) {
//		this.active = active;
//	}
//	public int getOtp() {
//		return otp;
//	}
//	public void setOtp(int otp) {
//		this.otp = otp;
//	}
//	public boolean isVerified() {
//		return verified;
//	}
//	public void setVerified(boolean verified) {
//		this.verified = verified;
//	}

	
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public String getQualification() {
		return qualification;
	}

	
	

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	
	
//	public Set<Paper> getPapers() {
//		return papers;
//	}
//
//	public void setPapers(Set<Paper> papers) {
//		this.papers = papers;
//	}
//	
	

	public Reviewer() {
		super();
	}
	
	
	
	
	
	
	public Reviewer(long id, String department, String qualification, Categories research_interests,
			String cvfile, String prooffile) {
		super();
		this.id = id;
		
		this.department = department;
		this.qualification = qualification;
		this.research_interests = research_interests;
		this.cvfile = cvfile;
		this.prooffile = prooffile;
	}
	public Reviewer( String department, String qualification, Categories research_interests, String cvfile,
			String prooffile) {
		super();
		
		this.department = department;
		this.qualification = qualification;
		this.research_interests = research_interests;
		this.cvfile = cvfile;
		this.prooffile = prooffile;
	}
	
	
	
	
	public Reviewer(Long id, @NotEmpty(message = "*Please provide First Name") String fname, String lname, String email,
			@Pattern(regexp = "^([1-9])\\d{9}", message = "*Please provide a valid contact number") String contact,
			String password, String line1, String line2, String city, String state, String country, String pincode,
			int active, int otp, boolean verified, Set<Role> roles) {
		super(id, fname, lname, email, contact, password, line1, line2, city, state, country, pincode, active, otp, verified,
				roles);
		// TODO Auto-generated constructor stub
	}

	public Reviewer(Long id, @NotEmpty(message = "*Please provide First Name") String fname, String lname, String email,
			@Pattern(regexp = "^([1-9])\\d{9}", message = "*Please provide a valid contact number") String contact,
			String password, String line1, String line2, String city, String state, String country, String pincode,
			String Institute, int active, int otp, boolean verified) {
		super(id, fname, lname, email, contact, password, line1, line2, city, state, country, pincode, Institute, active, otp,
				verified);
		// TODO Auto-generated constructor stub
	}

	public Reviewer(@NotEmpty(message = "*Please provide First Name") String fname, String lname, String email,
			@Pattern(regexp = "^([1-9])\\d{9}", message = "*Please provide a valid contact number") String contact,
			String password, String line1, String line2, String city, String state, String country, String pincode,
			String Institute, int active, int otp, boolean verified, Set<Role> roles) {
		super(fname, lname, email, contact, password, line1, line2, city, state, country, pincode, Institute, active, otp,
				verified, roles);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Reviewer [id=" + id + ", department=" + department + ", qualification="
				+ qualification + ", research_interests=" + research_interests + ", cvfile=" + cvfile + ", prooffile="
				+ prooffile + "]";
	}
	
	
	
	
	
}
