package com.company;

import java.util.ArrayList;
import java.util.List;

public class Student {
    public String name;
    public int group;
    public int course;
    public List<Integer> grades=new ArrayList<>();



    public Student(String name, int group, int course, List<Integer> grades){
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public List<Integer> getGrades() {
        return grades;
    }
}
