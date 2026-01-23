package com.example.demo.User;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class UpdateUserRequest {

    @Getter
    private String name;
    @Getter
    private String surname;
    private String eMail;
    @Getter
    private String password;
    private LocalDate birth;
    private String role;
    @Getter
    @Setter
    private String user_name;



    public UpdateUserRequest() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
