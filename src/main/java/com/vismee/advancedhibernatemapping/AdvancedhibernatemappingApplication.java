package com.vismee.advancedhibernatemapping;

import com.vismee.advancedhibernatemapping.dao.AppDAO;
import com.vismee.advancedhibernatemapping.entity.Course;
import com.vismee.advancedhibernatemapping.entity.Instructor;
import com.vismee.advancedhibernatemapping.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancedhibernatemappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedhibernatemappingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO)
	{
		return runner -> {
		       // createInstructor(appDAO);
		       // findInstructorById(appDAO);
			   // deleteInstructorById(appDAO);

			   // findInstructorDetailById(appDAO);
			   // deleteInstructorDetailById(appDAO);

			   // createInstructorWithCourses(appDAO);
			   // createCourse(appDAO);
			   // findCoursesForInstructor(appDAO);
			   // findInstructorwithCoursesJoinFetch(appDAO);
               // updateInstructor(appDAO);
			   // updateCourse(appDAO);
			   // deleteCourse(appDAO);
			   // deleteInstructorById(appDAO);

		};
	}

	private void deleteCourse(AppDAO appDAO)
	{
		int id = 13;
		appDAO.deleteCourseById(id);
	}

	private void updateCourse(AppDAO appDAO)
	{
		int id = 12;

		Course course = appDAO.findCourseById(id);
		course.setTitle("HTML & CSS");

		appDAO.updateCourse(course);
	}

	private void updateInstructor(AppDAO appDAO)
	{
		int id = 1;
		Instructor instructor = appDAO.findInstructorById(id);
		instructor.setEmail("soormo06@gmail.com");

		appDAO.updateInstructor(instructor);
	}

	private void findInstructorwithCoursesJoinFetch(AppDAO appDAO)
	{
        int id=1;
		Instructor instructor = appDAO.findInstructorWithCoursesJoinFetch(id);

		System.out.println(instructor);

		System.out.println(instructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO)
	{
		int id=1;
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor " +instructor);
		// Here we get error since by default one to many mapping has LAZY loading as default fetch type
		// System.out.println("Associated Instructor courses " +instructor.getCourses());

		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		System.out.println(courses);

		/* Associating instructor and courses by making use of two seperate queries .. one for fetching
		   instructor alone and another for fetching course and associating both entities
		 */
		instructor.setCourses(courses);
		System.out.println("Associated instructor courses " +instructor.getCourses());
	}

	private void createCourse(AppDAO appDAO)
	{
		int id = 1;
		Course course = new Course("Java 15");

		appDAO.createCourse(course,id);
	}

	private void createInstructorWithCourses(AppDAO appDAO)
	{
		Instructor instructor = new Instructor("Sooriya","Meenakshi","meenuvisu0702@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("Vismee Learnings","learning");
		instructor.setInstructorDetail(instructorDetail);

		Course course1 = new Course("Spring Boot and Hibernate");
		instructor.addCourse(course1);
		Course course2 = new Course("React JS");
		instructor.addCourse(course2);
		Course course3 = new Course("HTML, CSS and Javascript");
		instructor.addCourse(course3);

		appDAO.saveInstructor(instructor);
	}

	private void deleteInstructorDetailById(AppDAO appDAO)
	{
		System.out.println("Deleting instructor detail from db");
		int id=2;
		appDAO.deleteInstructorDetailById(id);
	}

	private void findInstructorDetailById(AppDAO appDAO)
	{
		System.out.println("Fetching Instructor details");
        int id = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("Instructor detail " + instructorDetail);
		System.out.println("Instructor " +instructorDetail.getInstructor());
	}


	private void deleteInstructorById(AppDAO appDAO)
	{
		System.out.println("Deleting instructor By Id");
		int id = 1;
		appDAO.deleteInstructorById(id);
	}

	private void findInstructorById(AppDAO appDAO)
	{
		System.out.println("Fetching instructor and details by id");
		int instructorId = 1;
		Instructor instructor = appDAO.findInstructorById(instructorId);
		System.out.println("Instructor : " + instructor);
		System.out.println("Instructor details : " +instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO)
	{
		Instructor instructor1 = new Instructor("Sooriya","Meenakshi","meenuvisu0702@gmail.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("vismeelearnings","learning");
		instructor1.setInstructorDetail(instructorDetail1);

		appDAO.saveInstructor(instructor1);


		Instructor instructor2 = new Instructor("Bhargav","Parithi","Parithi3101@gmail.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("parithilearnings","experimenting");
		instructor2.setInstructorDetail(instructorDetail2);

		appDAO.saveInstructor(instructor2);
	}

}
