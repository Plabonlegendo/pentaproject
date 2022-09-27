package com.example.pentaproject.dtos;

import com.example.pentaproject.model.Person;

import java.util.ArrayList;

public class GetResponse {
    private String message;

    private ArrayList<Person> data;

    public GetResponse(){ }

    public GetResponse(String message, ArrayList<Person> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Person> getData() {
        return data;
    }

    public void setData(ArrayList<Person> data) {
        this.data = data;
    }
}
