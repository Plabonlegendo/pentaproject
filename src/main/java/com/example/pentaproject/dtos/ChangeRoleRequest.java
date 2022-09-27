package com.example.pentaproject.dtos;

import javax.validation.constraints.NotBlank;

public class ChangeRoleRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String changedRole;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChangedRole() {
        return changedRole;
    }

    public void setChangedRole(String changedRole) {
        this.changedRole = changedRole;
    }
}
