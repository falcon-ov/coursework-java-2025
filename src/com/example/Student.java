package com.example;

public class Student {
    private String name;
    private int age;
    private double grade;
    private String faculty;

    public Student(String name, int age, double grade, String faculty) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGrade() {
        return grade;
    }

    public String getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", grade=" + grade + ", faculty='" + faculty + "'}";
    }
}