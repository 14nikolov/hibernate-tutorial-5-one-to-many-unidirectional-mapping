package com.tables.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {
	
	// Fields
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="comment")
	private String comment;

	// no variable is required to represent the "course_id" column
	// because in this case we have a @OneToMany Uni-Directional relationship
	
	// Constructors
	
	public Review() {}
	
	public Review(String comment) {
		this.comment = comment;
	}
	
	// toString method
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", comment=" + comment + "]";
	}

	// Getters, Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
	
	
}
