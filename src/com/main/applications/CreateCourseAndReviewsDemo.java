package com.main.applications;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tables.entities.Course;
import com.tables.entities.Instructor;
import com.tables.entities.InstructorDetail;
import com.tables.entities.Review;

public class CreateCourseAndReviewsDemo {
	
	public static void main(String[] args) {
	
	// creating and configuring session factory
	SessionFactory sessionFactory = new Configuration().
			configure("hibernate.cfg.xml").
			addAnnotatedClass(Instructor.class).
			addAnnotatedClass(InstructorDetail.class).
			addAnnotatedClass(Course.class).
			addAnnotatedClass(Review.class).
			buildSessionFactory();
	
	// creating a new session
	Session session = sessionFactory.getCurrentSession();
	
	// try-catch block that will make sure to release unused resources,
	// in case the transaction fails
	// also it will print the cause of the fail in the console
	try {
	
	// begin a new transaction
	session.beginTransaction();
	
	// Creating a new Course
	Course course = new Course("How to Eat 5");
	// Creating a Review
	Review review1 = new Review("great course");
	// Adding that review to our course
	course.addReview(review1);
	// creating and adding reviews for our course
	course.addReview(new Review("worst course I ever taken in my life"));
	course.addReview(new Review("well done course from start to finish"));
	
	// Logging messages
	System.out.println("Saving course");
	System.out.println(course);
	System.out.println("Saving reviews for course");
	System.out.println(course.getReviews());
	session.save(course);
	
	// save changes performed during transaction and end transaction
	session.getTransaction().commit();
	
	
	}catch(Exception e) {
		System.out.println("Transaction failed");
		System.out.println("Closing session");
		session.close();
		sessionFactory.close();
		e.printStackTrace();
	}
	
	
	}
}
