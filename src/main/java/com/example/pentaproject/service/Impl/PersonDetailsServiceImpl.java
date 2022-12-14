package com.example.pentaproject.service.Impl;

import com.example.pentaproject.exception.UserNotFoundException;
import com.example.pentaproject.model.Person;
import com.example.pentaproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmailId(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with emailId: " + email));


        if(person.isActive()){
            return PersonDetailsImpl.build(person);
        }else {
            throw new UserNotFoundException();
        }

    }
}
