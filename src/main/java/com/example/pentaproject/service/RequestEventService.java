package com.example.pentaproject.service;

import com.example.pentaproject.model.Person;
import com.example.pentaproject.model.RequestEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface RequestEventService {
    void sendsRequest(Integer studentId, Integer teacherId, String eventName);

    ArrayList<Person> findAllTeachersRequests(Integer id);

    RequestEvent searchEventByTeacherStudent(Integer studentId, Integer teacherId);

    void deleteEventById(Integer id);

    ArrayList<Person> getStudentsList(Integer id);
}
