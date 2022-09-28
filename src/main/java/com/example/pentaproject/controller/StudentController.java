package com.example.pentaproject.controller;


import com.example.pentaproject.dtos.ChangeRoleRequest;
import com.example.pentaproject.dtos.MessageResponse;
import com.example.pentaproject.dtos.RequestEventDto;
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
    public ResponseEntity<?> changeRole(@Valid @RequestBody RequestEventDto requestEventDto){
        requestEventService.sendsRequest(requestEventDto.getStudentId(), requestEventDto.getTeacherId(), requestEventDto.getEventName());
        return ResponseEntity.ok(new MessageResponse("Request Sent Successfully"));
    }

//    @GetMapping("resources/student/teacher_list/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Student')")
//    public ResponseEntity<?> getTeachers(@PathVariable Integer id){
//        Person student = personService.getPersonById(id);
//
//        if(student != null && student.getAdvisorId() != null){
//            ArrayList<Person> teacher = new ArrayList<Person>(Arrays.asList(personService.getPersonById(student.getAdvisorId())));
//        }
//    }
}
