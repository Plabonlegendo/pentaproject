package com.example.pentaproject.service.Impl;

import com.example.pentaproject.model.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonDetailsImpl implements UserDetails {
//    private Person person;

    private Integer id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private boolean isActive;

    private Collection<? extends GrantedAuthority> authorities;

    public PersonDetailsImpl(Integer id, String username, String email, String password, boolean isActive,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.authorities = authorities;
    }

    public static PersonDetailsImpl build(Person person) {
        String ROLE_PREFIX = "ROLE_";
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + person.getRole()));

        return new PersonDetailsImpl(
                person.getId(),
                person.getName(),
                person.getEmailId(),
                person.getPassword(),
                person.isActive(),
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PersonDetailsImpl user = (PersonDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

}
