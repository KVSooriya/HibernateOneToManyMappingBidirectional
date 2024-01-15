package com.vismee.advancedhibernatemapping.dao;

import com.vismee.advancedhibernatemapping.entity.Course;
import com.vismee.advancedhibernatemapping.entity.Instructor;
import com.vismee.advancedhibernatemapping.entity.InstructorDetail;

import java.util.List;

public interface AppDAO
{
    void saveInstructor(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    void createCourse(Course course,int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorWithCoursesJoinFetch(int id);
    void updateInstructor(Instructor instructor);
    Course findCourseById(int id);
    void updateCourse(Course course);
    void deleteCourseById(int id);
}
