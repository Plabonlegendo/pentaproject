package com.example.pentaproject.service.Impl;

import com.example.pentaproject.dtos.RequestEventDto;
import com.example.pentaproject.model.Person;
import com.example.pentaproject.model.RequestEvent;
import com.example.pentaproject.repository.PersonRepository;
import com.example.pentaproject.repository.RequestEventRepository;
import com.example.pentaproject.service.RequestEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestEventServiceImpl implements RequestEventService {
    @Autowired
    RequestEventRepository requestEventRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public void sendsRequest(Integer studentId, Integer teacherId, String eventName) {
        Person student = personRepository.findById(studentId)
                .orElseThrow(() -> new UsernameNotFoundException("Student Not Found with emailId: " + studentId));

        Person teacher = personRepository.findById(teacherId)
                .orElseThrow(() -> new UsernameNotFoundException("Teacher Not Found with emailId: " + teacherId));


        RequestEvent requestEvent = new RequestEvent(teacher, student, eventName);

        requestEventRepository.save(requestEvent);
    }

    @Override
    public ArrayList<Person> findAllTeachersRequests(Integer id) {
        ArrayList<Person> persons = personRepository.findAllStudentsRequestsByTeacherId(id);
        return persons;
    }

    @Override
    public RequestEvent searchEventByTeacherStudent(Integer studentId, Integer teacherId) {
        Person student = personRepository.findById(studentId)
                .orElseThrow(() -> new UsernameNotFoundException("Student Not Found with emailId: " + studentId));
        Person teacher = personRepository.findById(teacherId)
                .orElseThrow(() -> new UsernameNotFoundException("Teacher Not Found with emailId: " + teacherId));
        RequestEvent requestEvent = requestEventRepository.findByTeacherAndStudent(teacher, student);

        return requestEvent;
    }

    @Override
    public void deleteEventById(Integer id) {
        requestEventRepository.deleteById(id);
    }

    @Override
    public ArrayList<Person> getStudentsList(Integer id) {
        return personRepository.findAllByAdvisorId(id);
    }
}
