package com.example.pentaproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    @NotNull
    @Column(name = "name")
    @Size(max = 100)
    private String name;

    @NotNull
    @Column(name = "phone_no", unique = true)
    private String phoneNo;

    @NotNull
    @Column(name = "email_id", unique = true)
    private String emailId;

    @NotNull
    @Column(name = "department_name")
    private String departmentName;

    @NotNull
    @Column(name = "role")
    private String role;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "advisor_id")
    private Integer advisorId;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private List<RequestEvent> requestEventsList = new ArrayList<>();

    public Person(){ }

    public Person(String name, String phoneNo, String emailId, String departmentName, String role, String password, boolean isActive){
        this.name  = name;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.departmentName = departmentName;
        this.role = role;
        this.password = password;
        this.isActive = isActive;
    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(Integer advisorId) {
        this.advisorId = advisorId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }
}
