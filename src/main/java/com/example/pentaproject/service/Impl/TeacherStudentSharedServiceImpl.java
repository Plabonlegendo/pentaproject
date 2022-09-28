package com.example.pentaproject.service.Impl;

import com.example.pentaproject.dtos.EditProfileRequest;
import com.example.pentaproject.model.Person;
import com.example.pentaproject.repository.PersonRepository;
import com.example.pentaproject.service.TeacherStudentSharedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TeacherStudentSharedServiceImpl implements TeacherStudentSharedService {
    @Autowired
    PersonRepository personRepository;


    @Override
    public Person updateProfile(Integer id, EditProfileRequest editProfileRequest) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with emailId: " + id));

        if(person != null){
            person.setName(editProfileRequest.getUsername());
            person.setPhoneNo(editProfileRequest.getPhoneNo());
            person.setEmailId(editProfileRequest.getEmail());
        }

        try{
            return personRepository.save(person);
        }catch (Exception ex){
            throw new RuntimeException("Update is not successful");
        }

    }
}
