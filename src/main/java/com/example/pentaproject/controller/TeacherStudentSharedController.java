package com.example.pentaproject.controller;


import com.example.pentaproject.dtos.*;
import com.example.pentaproject.exception.UserNotFoundException;
import com.example.pentaproject.model.Person;
import com.example.pentaproject.service.PersonService;
import com.example.pentaproject.service.TeacherStudentSharedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TeacherStudentSharedController {
    @Autowired
    TeacherStudentSharedService teacherStudentSharedService;

    @Autowired
    PersonService personService;

    @Autowired
    ModelMapper mapper;

    @GetMapping(value = "resources/get_profile/{id}")
    @PreAuthorize("hasAuthority('ROLE_Teacher') or hasAuthority('ROLE_Student')")
    public ResponseEntity<?> getPersonProfile(@PathVariable Integer id){
        Person person = personService.getPersonById(id);
        if(person == null){
            throw new UserNotFoundException();
        }

        return ResponseEntity.ok(new GetPersonResponse("Profile found Successfully", new PersonDto(person.getId(),
                person.getName(), person.getPhoneNo(), person.getEmailId(), person.getDepartmentName(),
                person.getRole(), person.getAdvisorId())));
    }

    @PutMapping("resources/edit_profile/{id}")
    @PreAuthorize("hasAuthority('ROLE_Teacher') or hasAuthority('ROLE_Student')")
    public ResponseEntity<?> editPersonProfile(@PathVariable(value = "id") Integer id, @RequestBody EditProfileRequest editProfileRequest){
        teacherStudentSharedService.updateProfile(id, editProfileRequest);

        return ResponseEntity.ok(new MessageResponse("Profile Updated Successfully"));
    }

}
