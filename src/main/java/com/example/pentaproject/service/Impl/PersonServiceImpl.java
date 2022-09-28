package com.example.pentaproject.service.Impl;

import com.example.pentaproject.model.Person;
import com.example.pentaproject.repository.PersonRepository;
import com.example.pentaproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Person createPerson(Person person) {
        try{
            return personRepository.save(person);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person updatePerson(Person person) {
        try{
            return personRepository.save(person);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Person getPersonByEmailId(String email) {
        return personRepository.findByEmailId(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with emailId: " + email));
    }

    @Override
    public Person getPersonById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with emailId: " + id));
    }


    @Override
    public Boolean isExistsPersonEmailId(String emailId) {
        return personRepository.existsByEmailId(emailId);
    }

    @Override
    public Boolean isExistsPersonByPhoneNo(String phoneNo) {
        return personRepository.existsByPhoneNo(phoneNo);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        Person person = personRepository.findByEmailId(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with emailId: " + email));
        if (person != null) {
            person.setResetPasswordToken(token);
            personRepository.save(person);
        } else {
            throw new RuntimeException("Could not find any teacher/student with the email " + email);
        }
    }

    @Override
    public Person getByResetPasswordToken(String token) {
        return personRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(Person person, String newPassword) {
        String encodedPassword = encoder.encode(newPassword);
        person.setPassword(encodedPassword);

        person.setResetPasswordToken(null);
        personRepository.save(person);
    }

    @Override
    public ArrayList<Person> getStudentsList(Integer id) {
        return personRepository.findAllByAdvisorId(id);
    }
}
