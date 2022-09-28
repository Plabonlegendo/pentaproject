package com.example.pentaproject.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestEventDto {
    @NotNull
    private Integer studentId;

    @NotNull
    private Integer teacherId;

    @NotBlank
    private String eventName;

    public RequestEventDto(Integer studentId, Integer teacherId, String eventName){
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.eventName = eventName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
