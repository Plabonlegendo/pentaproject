package com.example.pentaproject.service.Impl;

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

        return new PersonDetailsImpl(person);
    }
}
