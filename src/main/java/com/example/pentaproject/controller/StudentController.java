package com.example.pentaproject.controller;


import com.example.pentaproject.dtos.*;
import com.example.pentaproject.exception.InvalidEventNameInputException;
import com.example.pentaproject.exception.UserNotFoundException;
import com.example.pentaproject.model.Person;
import com.example.pentaproject.service.PersonService;
import com.example.pentaproject.service.RequestEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    PersonService personService;

    @Autowired
    RequestEventService requestEventService;

    @PostMapping("resources/student/sends_request")
    @PreAuthorize("hasAuthority('ROLE_Student')")
    public ResponseEntity<?> sendsRequestTeacher(@Valid @RequestBody RequestEventDto requestEventDto){
        if(requestEventDto.getEventName().equalsIgnoreCase("Sent")){
            requestEventService.sendsRequest(requestEventDto.getStudentId(), requestEventDto.getTeacherId(), requestEventDto.getEventName());
        }else{
            throw new InvalidEventNameInputException();
        }
        return ResponseEntity.ok(new MessageResponse("Request Sent Successfully"));
    }

    @GetMapping("resources/student/teacher_list/{id}")
    @PreAuthorize("hasAuthority('ROLE_Student')")
    public ResponseEntity<?> getTeachers(@PathVariable Integer id){
        Person student = personService.getPersonById(id);
        ArrayList<PersonDto> teachers = new ArrayList<PersonDto>();
        if(student != null){
            if(student.getAdvisorId() != null){
                Person teacher = personService.getPersonById(student.getAdvisorId());
                teachers = new ArrayList<PersonDto>(Arrays.asList(new PersonDto(teacher.getId(),
                        teacher.getName(), teacher.getPhoneNo(), teacher.getEmailId(), teacher.getDepartmentName(),
                        teacher.getRole(), null)));
            }else {
                ArrayList<Person> teachersActual = personService.getAllTeachersFromDepartment(student.getDepartmentName(), "Teacher");
                for(Person teacher: teachersActual){
                    teachers.add(new PersonDto(teacher.getId(),
                            teacher.getName(), teacher.getPhoneNo(), teacher.getEmailId(), teacher.getDepartmentName(),
                            teacher.getRole(), null));
                }
            }
        }else{
            throw new UserNotFoundException();
        }

        return ResponseEntity.ok(new GetResponse("Data Found Successfully", teachers));
    }
}
