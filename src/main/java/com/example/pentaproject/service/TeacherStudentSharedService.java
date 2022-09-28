package com.example.pentaproject.service;

import com.example.pentaproject.dtos.EditProfileRequest;
import com.example.pentaproject.model.Person;

public interface TeacherStudentSharedService {
    Person updateProfile(Integer id, EditProfileRequest editProfileRequest);
}
