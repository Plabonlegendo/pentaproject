package com.example.pentaproject.dtos;

import com.example.pentaproject.model.Person;

public class GetPersonResponse {
    private String message;

    private Person person;

    public GetPersonResponse(String message, Person person) {
        this.message = message;
        this.person = person;
    }
    public GetPersonResponse(String message){
        this.message = message;
    }
}
