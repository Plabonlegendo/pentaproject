package com.example.pentaproject.dtos;

import com.example.pentaproject.model.Person;

public class GetJwtResponse {
    private String message;

    private JwtResponse data;

    public GetJwtResponse(String message, JwtResponse jwtResponse) {
        this.message = message;
        this.data = jwtResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JwtResponse getData() {
        return data;
    }

    public void setData(JwtResponse data) {
        this.data = data;
    }
}
