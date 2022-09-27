package com.example.pentaproject.dtos;

import javax.validation.constraints.NotBlank;

public class ChangeActiveStatusRequest {
    @NotBlank
    private String email;

    private boolean changedStatus;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isChangedStatus() {
        return changedStatus;
    }

    public void setChangedStatus(boolean changedStatus) {
        this.changedStatus = changedStatus;
    }
}
