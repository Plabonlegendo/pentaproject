package com.example.pentaproject.service;

import com.example.pentaproject.model.Person;

public interface PersonService {
    Person createPerson(Person person);

    Boolean isExistsPersonEmailId(String emailId);

    Boolean isExistsPersonByPhoneNo(String phoneNo);

    void updateResetPasswordToken(String token, String email);

    Person getByResetPasswordToken(String token);

    void updatePassword(Person person, String newPassword);
}
