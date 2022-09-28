package com.example.pentaproject.repository;

import com.example.pentaproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmailId(String email);

    Optional<Person> findById(Integer Id);

    Boolean existsByEmailId(String emailId);

    Boolean existsByPhoneNo(String phoneNo);

    Person findByResetPasswordToken(String token);

    @Query(value = "select * from PERSONS p where p.role != 'Admin'", nativeQuery = true)
    ArrayList<Person> findAllPersons();

    @Query(value = "Select * from persons where id in (Select student_id from request_event where teacher_id = id)", nativeQuery = true)
    ArrayList<Person> findAllStudentsRequestsByTeacherId(Integer id);

    ArrayList<Person> findAllByAdvisorId(Integer id);

    ArrayList<Person> findAllByDepartmentNameAndRole(String departmentName, String Role);
}
