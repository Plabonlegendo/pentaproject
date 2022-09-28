package com.example.pentaproject.controller;

import com.example.pentaproject.dtos.*;
import com.example.pentaproject.model.Person;
import com.example.pentaproject.service.AdminService;
import com.example.pentaproject.service.Impl.AdminServiceImpl;
import com.example.pentaproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    PersonService personService;

    @GetMapping("resources/admin")
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    public ResponseEntity<?> getTeachersAndStudents(){
        ArrayList<Person> personsActual = adminService.findAllTeachersAndStudents();

        ArrayList<PersonDto> persons = new ArrayList<PersonDto>();
        for(Person person: personsActual){
            persons.add(new PersonDto(person.getId(),
                    person.getName(), person.getPhoneNo(), person.getEmailId(), person.getDepartmentName(),
                    person.getRole(), null));
        }

        if(persons == null){
            return ResponseEntity.ok(new GetResponse("Teachers and Students Not Found", null));
        }

        return ResponseEntity.ok(new GetResponse("Data Found Successfully", persons));
    }

    @PutMapping("resources/admin/change_role")
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    public ResponseEntity<?> changeRole(@Valid @RequestBody ChangeRoleRequest changeRoleRequest){
        Person person = personService.getPersonByEmailId(changeRoleRequest.getEmail());

        person.setRole(changeRoleRequest.getChangedRole());

        personService.updatePerson(person);

        return ResponseEntity.ok(new MessageResponse("Role Updated Successfully"));
    }

    @PutMapping("resources/admin/change_active_status")
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    public ResponseEntity<?> changeActiveStatus(@Valid @RequestBody ChangeActiveStatusRequest changeActiveStatusRequest){
        Person person = personService.getPersonByEmailId(changeActiveStatusRequest.getEmail());

        person.setActive(changeActiveStatusRequest.isChangedStatus());

        personService.updatePerson(person);

        return ResponseEntity.ok(new MessageResponse("Active Status Changed Successfully"));

    }
}
