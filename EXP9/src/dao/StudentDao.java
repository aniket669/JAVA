package com.example.dao;

import java.util.List;

import com.example.entity.Student;

public interface StudentDao {
    Integer save(Student student);
    Student get(Integer id);
    void update(Student student);
    void delete(Integer id);
    List<Student> list();
}
