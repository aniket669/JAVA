package com.example.service;

import java.util.List;

import com.example.entity.Student;

public interface StudentService {
    Integer createStudent(Student student);
    Student findStudent(Integer id);
    void modifyStudent(Student student);
    void removeStudent(Integer id);
    List<Student> allStudents();
    void transactionalDemo();
}
