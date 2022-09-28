package com.example.pentaproject.repository;

import com.example.pentaproject.model.Person;
import com.example.pentaproject.model.RequestEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface RequestEventRepository extends JpaRepository<RequestEvent, Integer> {
    ArrayList<RequestEvent> findAllByTeacher(Person person);

    RequestEvent findByTeacherAndStudent(Person teacher, Person student);

    void deleteById(Integer id);
    void delete(RequestEvent requestEvent);
}
