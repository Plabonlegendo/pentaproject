package com.example.pentaproject.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangeRoleRequest {
    @NotNull
    private Integer id;

    @NotBlank
    private String changedRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChangedRole() {
        return changedRole;
    }

    public void setChangedRole(String changedRole) {
        this.changedRole = changedRole;
    }
}
