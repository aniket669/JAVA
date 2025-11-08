package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Integer save(Student student) {
        return (Integer) currentSession().save(student);
    }

    public Student get(Integer id) {
        return currentSession().get(Student.class, id);
    }

    public void update(Student student) {
        currentSession().update(student);
    }

    public void delete(Integer id) {
        Student s = get(id);
        if (s != null) currentSession().delete(s);
    }

    @SuppressWarnings("unchecked")
    public List<Student> list() {
        return currentSession().createQuery("from Student").list();
    }
}
