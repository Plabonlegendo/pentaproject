package com.example.pentaproject.dtos;

import com.example.pentaproject.model.Person;

import java.util.ArrayList;
import java.util.List;

public class GetResponse {
    private String message;

    private List<PersonDto> data;

    public GetResponse(){ }

    public GetResponse(String message, ArrayList<PersonDto> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PersonDto> getData() {
        return data;
    }

    public void setData(List<PersonDto> data) {
        this.data = data;
    }
}
