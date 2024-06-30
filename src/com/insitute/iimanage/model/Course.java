package com.insitute.iimanage.model;

public class Course {

    private String courseId;
    private String courseName;
    private String[] subjects;
    private String teacherId;
    private double cost;

    public Course() {
    }

    public Course(String courseId, String courseName, String[] subjects, String teacherId, double cost) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.subjects = subjects;
        this.teacherId = teacherId;
        this.cost = cost;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
