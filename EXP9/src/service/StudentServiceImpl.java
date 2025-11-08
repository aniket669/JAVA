package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.StudentDao;
import com.example.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Transactional
    public Integer createStudent(Student student) {
        return studentDao.save(student);
    }

    @Transactional(readOnly = true)
    public Student findStudent(Integer id) {
        return studentDao.get(id);
    }

    @Transactional
    public void modifyStudent(Student student) {
        studentDao.update(student);
    }

    @Transactional
    public void removeStudent(Integer id) {
        studentDao.delete(id);
    }

    @Transactional(readOnly = true)
    public List<Student> allStudents() {
        return studentDao.list();
    }

    @Transactional
    public void transactionalDemo() {
        Student s1 = new Student("Alice", 20, "A");
        Student s2 = new Student("Bob", 21, "B");
        studentDao.save(s1);
        studentDao.save(s2);
        if (true) throw new RuntimeException("Force rollback");
    }
}
