package com.example.pentaproject.dtos;

public class PersonDto {
    private Integer id;

    private String name;

    private String phoneNo;

    private String email;

    private String departmentName;

    private String role;

    private Integer advisorId;

    private boolean isActive;

    public PersonDto(Integer id, String name, String phone, String email, String departmentName, String role, Integer advisorId, boolean isActive) {
        this.id = id;
        this.name = name;
        this.phoneNo = phone;
        this.email = email;
        this.departmentName = departmentName;
        this.role = role;
        this.advisorId = advisorId;
        this.isActive = isActive;
    }

    public PersonDto(Integer id, String name, String phone, String email, String departmentName, String role, Integer advisorId) {
        this.id = id;
        this.name = name;
        this.phoneNo = phone;
        this.email = email;
        this.departmentName = departmentName;
        this.role = role;
        this.advisorId = advisorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phoneNo;
    }

    public void setPhone(String phone) {
        this.phoneNo = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(Integer advisorId) {
        this.advisorId = advisorId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
