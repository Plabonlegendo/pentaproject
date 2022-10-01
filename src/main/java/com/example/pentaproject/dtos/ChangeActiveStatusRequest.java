package com.example.pentaproject.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangeActiveStatusRequest {
    @NotNull
    private Integer id;

    @NotNull
    private boolean changedStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isChangedStatus() {
        return changedStatus;
    }

    public void setChangedStatus(boolean changedStatus) {
        this.changedStatus = changedStatus;
    }
}
