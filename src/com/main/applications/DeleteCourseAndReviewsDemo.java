package com.main.applications;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tables.entities.Course;
import com.tables.entities.Instructor;
import com.tables.entities.InstructorDetail;
import com.tables.entities.Review;

public class DeleteCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				addAnnotatedClass(Review.class).
				buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		try {
			int courseId = 18;
			Course course = session.get(Course.class, courseId);
			session.delete(course);
			
			session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("\n\n\nTransaction Failed");
			System.out.println("Closing Session and SessionFactory\n\n\n");
			session.close();
			sessionFactory.close();
			e.printStackTrace();
		}
	}
	
}
