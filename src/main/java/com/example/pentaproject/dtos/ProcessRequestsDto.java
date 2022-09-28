package com.example.pentaproject.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProcessRequestsDto {
    @NotNull
    private Integer studentId;

    @NotNull
    private Integer teacherId;

    private String eventName;

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public String getEventName() {
        return eventName;
    }
}
