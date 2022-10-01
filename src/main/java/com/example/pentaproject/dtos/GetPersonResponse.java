package com.example.pentaproject.dtos;

import com.example.pentaproject.model.Person;

public class GetPersonResponse {
    private String message;

    private PersonDto personDto;

    public GetPersonResponse(String message, PersonDto personDto) {
        this.message = message;
        this.personDto = personDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonDto getPersonDto() {
        return personDto;
    }

    public void setPersonDto(PersonDto personDto) {
        this.personDto = personDto;
    }
}
