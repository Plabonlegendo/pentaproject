package com.example.pentaproject.controller;

import com.example.pentaproject.dtos.*;
import com.example.pentaproject.model.Person;
import com.example.pentaproject.model.RequestEvent;
import com.example.pentaproject.service.PersonService;
import com.example.pentaproject.service.RequestEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {
    @Autowired
    PersonService personService;

    @Autowired
    RequestEventService requestEventService;

    @GetMapping("resources/teacher/requests/{id}")
    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    public ResponseEntity<?> getStudentsRequests(@PathVariable Integer id){
        ArrayList<Person> studentsActual = requestEventService.findAllTeachersRequests(id);

        ArrayList<PersonDto> students = new ArrayList<PersonDto>();
        for(Person student: studentsActual){
            students.add(new PersonDto(student.getId(),
                    student.getName(), student.getPhoneNo(), student.getEmailId(), student.getDepartmentName(),
                    student.getRole(), student.getAdvisorId()));
        }

        if(studentsActual == null){
            return ResponseEntity.ok(new GetResponse("Teachers and Students Not Found", null));
        }

        return ResponseEntity.ok(new GetResponse("Data Found Successfully", students));
    }

    @PostMapping("resources/teacher/process_requests")
    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    public ResponseEntity<?> processRequests(@Valid @RequestBody ProcessRequestsDto processRequestsDto){
        RequestEvent requestEvent = requestEventService.searchEventByTeacherStudent(processRequestsDto.getStudentId(), processRequestsDto.getTeacherId());

//        if(requestEvent == null){
//            throw new EventNotFound();
//        }
        if(processRequestsDto.getEventName().equals("Accepted")){
            Person student = personService.getPersonById(processRequestsDto.getStudentId());
            student.setAdvisorId(processRequestsDto.getTeacherId());
            personService.updatePerson(student);
        }

        requestEventService.deleteEventById(requestEvent.getId());

        return ResponseEntity.ok(new MessageResponse("Request is Processed Successfully"));
    }


    @GetMapping("resources/teacher/students_list/{id}")
    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    public ResponseEntity<?> getStudents(@PathVariable Integer id){
        ArrayList<Person> studentsActual = personService.getStudentsList(id);

        ArrayList<PersonDto> students = new ArrayList<PersonDto>();
        for(Person student: studentsActual){
            students.add(new PersonDto(student.getId(),
                    student.getName(), student.getPhoneNo(), student.getEmailId(), student.getDepartmentName(),
                    student.getRole(), student.getAdvisorId()));
        }

        return ResponseEntity.ok(new GetResponse("Students Found Successfully", students));
    }

    @PostMapping("resources/teacher/student_profile")
    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    public ResponseEntity<?> studentProfile(@Valid @RequestBody StudentTeacherRequest studentProfileRequest){
        Person student = personService.getPersonById(studentProfileRequest.getStudentId());

        if(student != null && student.getAdvisorId() == studentProfileRequest.getTeacherId()){
            return ResponseEntity.ok(student);
        }else {
            throw new RuntimeException("Request is not Acceptable");
        }
    }

    @PostMapping("resources/teacher/student_remove")
    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    public ResponseEntity<?> removeStudent(@Valid @RequestBody StudentTeacherRequest studentProfileRequest){
        Person student = personService.getPersonById(studentProfileRequest.getStudentId());

        if(student != null && student.getAdvisorId() == studentProfileRequest.getTeacherId()){
            student.setAdvisorId(null);
            personService.updatePerson(student);
            return ResponseEntity.ok(new MessageResponse("Student Removed Successfully"));
        }else{
            throw new RuntimeException("Invalid Request");
        }
    }
}
