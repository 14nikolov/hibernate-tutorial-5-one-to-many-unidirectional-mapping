package com.main.applications;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tables.entities.Course;
import com.tables.entities.Instructor;
import com.tables.entities.InstructorDetail;
import com.tables.entities.Review;

public class GetCourseAndReviewsDemo {
	
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
	
	int courseId = 16;
	Course tempCourse = session.get(Course.class, courseId);
	List<Review> reviews = tempCourse.getReviews();
	
	System.out.println("\n\n\n-----------COURSE: " + tempCourse + "\n\n\n");
	System.out.println("\n\n\n-----------REVIEWS: " + reviews + "\n\n\n");
	
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
