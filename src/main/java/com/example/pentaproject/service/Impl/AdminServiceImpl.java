package com.example.pentaproject.service.Impl;

import com.example.pentaproject.model.Person;
import com.example.pentaproject.repository.PersonRepository;
import com.example.pentaproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    PersonRepository personRepository;


    @Override
    public ArrayList<Person> findAllTeachersAndStudents() {
        return personRepository.findAllPersons();
    }
}
