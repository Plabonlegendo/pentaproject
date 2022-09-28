package com.example.pentaproject.dtos;

import javax.validation.constraints.NotNull;

public class StudentTeacherRequest {
    @NotNull
    private Integer studentId;

    @NotNull
    private Integer teacherId;

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }
}
