package com.example.pentaproject.repository;

import com.example.pentaproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmailId(String email);

    Boolean existsByEmailId(String emailId);

    Boolean existsByPhoneNo(String phoneNo);

    Person findByResetPasswordToken(String token);
}
