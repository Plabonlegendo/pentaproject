package com.example.pentaproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "request_event")
public class RequestEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, optional = false)
    @JoinColumn(name = "teacher_id")
    private Person teacher;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, optional = false)
    @JoinColumn(name = "student_id")
    private Person student;

    @NotBlank
    @Column(name = "event_name")
    private String eventName;

    public RequestEvent(){ }

    public RequestEvent(Person teacher, Person student, String eventName){
        this.teacher = teacher;
        this.student = student;
        this.eventName = eventName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestEvent)) return false;
        RequestEvent that = (RequestEvent) o;
        return Objects.equals(teacher.getEmailId(), that.teacher.getEmailId()) &&
                Objects.equals(student.getEmailId(), that.student.getEmailId()) &&
                Objects.equals(eventName, that.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacher.getEmailId(), student.getEmailId(), eventName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
