package com.vismee.advancedhibernatemapping.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructor")
public class Instructor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    /* OneToMany - Instructor can have one to many courses created by him
       FetchType.LAZY - If we retrieve instructor, it wont fetch courses immediately as like eager loading .. it will fetch courses only on demand
       Do not apply cascading delete .. ie. If we delete instructor, do not delete courses
     */
    @OneToMany(mappedBy = "instructor",
               fetch = FetchType.LAZY,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor()
    {

    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }

    // Convenience method for adding course to the instructor
    public void addCourse(Course tempCourse)
    {
        if(courses == null)
        {
            courses = new ArrayList<>();
        }
        courses.add(tempCourse);
        /*
          Adding bidirectional link .. ie. setting foreign key for the course to refer to instructor created
         */
        tempCourse.setInstructor(this);
    }
}