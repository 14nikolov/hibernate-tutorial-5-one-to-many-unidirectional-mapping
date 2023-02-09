package com.tables.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	
	// Fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,
			CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructorId;
	
	// do not load reviews of course, unless we explicitly request them (aka lazy fetch)
	// whatever we do with course, it will take effect on reviews (aka cascade type set to all)
	// for example if we delete course, reviews get deleted too 
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	// points directly to the column of Review Entity
	@JoinColumn(name="course_id")
	private List<Review> reviews;
	
	// Constructors
	
	public Course() {}

	public Course(String title) {
		this.title = title;
	}

	public Course(String title, Instructor instructorId) {
		this.title = title;
		this.instructorId = instructorId;
	}

	// toString

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", instructor=" + instructorId + "]";
	}

	
	// Getters and Setters
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructorId;
	}

	public void setInstructor(Instructor instructorId) {
		this.instructorId = instructorId;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	// convenience method

	public void addReview(Review tempReview) {
		
		// if the List reviews variable has not been instantiated, we instantiate it,
		// in order to be able to use it (add reviews to the list)
		if(reviews==null) {
			reviews = new ArrayList<Review>();
		}
		
		reviews.add(tempReview);
		
	}
	
}
