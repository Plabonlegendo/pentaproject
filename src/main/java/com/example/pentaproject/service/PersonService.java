package com.example.pentaproject.service;

import com.example.pentaproject.model.Person;

import java.util.ArrayList;

public interface PersonService {
    Person createPerson(Person person);

    Person updatePerson(Person person);

    Person getPersonByEmailId(String email);

    Person getPersonById(Integer id);

    Boolean isExistsPersonEmailId(String emailId);

    Boolean isExistsPersonByPhoneNo(String phoneNo);

    void updateResetPasswordToken(String token, String email);

    Person getByResetPasswordToken(String token);

    void updatePassword(Person person, String newPassword);

    ArrayList<Person> getStudentsList(Integer id);

    ArrayList<Person> getAllTeachersFromDepartment(String departmentName, String Role);
}
