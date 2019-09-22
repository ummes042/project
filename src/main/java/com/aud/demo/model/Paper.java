package com.aud.demo.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Paper {

	        @Id
	        @GeneratedValue(strategy = GenerationType.IDENTITY)
	        Long id;
	
			@NotEmpty(message="*please provide title")
			String title;
			
			
			@Size(min = 5 ,message = "*Please provide atleast 5 keywords")
			String keywords;
			
			String description;
			String filename;
			String comments;
			String experts;
			
			@JsonIgnore
			@ManyToOne
			User author;
			
			@Enumerated(EnumType.STRING)
		    PaperStatus status;
			
			
			@Enumerated(EnumType.STRING)
		    Categories category;
			
//			@ManyToOne
//			private Author author;
//			
//			@ManyToMany(mappedBy = "papers")
//			List<Reviewer> reviewers;
//			
			
			
			@JsonIgnore
			public User getAuthor() {
				return author;
			}

			public void setAuthor(User author) {
				this.author = author;
			}
			
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getKeywords() {
				return keywords;
			}

			public void setKeywords(String keywords) {
				this.keywords = keywords;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getFilename() {
				return filename;
			}

			public void setFilename(String filename) {
				this.filename = filename;
			}

			public String getComments() {
				return comments;
			}

			public void setComments(String comments) {
				this.comments = comments;
			}

			public String getExperts() {
				return experts;
			}

			public void setExperts(String experts) {
				this.experts = experts;
			}

			public Categories getCategory() {
				return category;
			}

			public void setCategory(Categories category) {
				this.category = category;
			}

			
			
			public PaperStatus getStatus() {
				return status;
			}

			public void setStatus(PaperStatus status) {
				this.status = status;
			}

			public Paper(long id, @NotEmpty(message = "*please provide title") String title,
					@Size(min = 5, message = "*Please provide atleast 5 keywords") String keywords, String description,
					String filename, String comments, String experts, Categories category) {
				super();
				this.id = id;
				this.title = title;
				this.keywords = keywords;
				this.description = description;
				this.filename = filename;
				this.comments = comments;
				this.experts = experts;
				this.category = category;
			}

			public Paper(@NotEmpty(message = "*please provide title") String title,
					@Size(min = 5, message = "*Please provide atleast 5 keywords") String keywords, String description,
					String filename, String comments, String experts, Categories category) {
				super();
				this.title = title;
				this.keywords = keywords;
				this.description = description;
				this.filename = filename;
				this.comments = comments;
				this.experts = experts;
				this.category = category;
			}

			public Paper() {
				super();
			}

			@Override
			public String toString() {
				return "Paper [id=" + id + ", title=" + title + ", keywords=" + keywords + ", desicription="
						+ description + ", filename=" + filename + ", Comments=" + comments + ", experts=" + experts
						+ ", category=" + category + "]";
			}
			
		

//			public Author getAuthor() {
//				return author;
//			}
//
//			public void setAuthor(Author author) {
//				this.author = author;
//			}
//
//			public List<Reviewer> getReviewers() {
//				return reviewers;
//			}
//
//			public void setReviewers(List<Reviewer> reviewers) {
//				this.reviewers = reviewers;
//			}
//
//			

	}



