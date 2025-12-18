package com.example.demo.User;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table

public class UserOfPopster {
    @Id
    @SequenceGenerator(name="userOfPopster_sequence", allocationSize = 1, sequenceName = "userOfPopster_sequence")
    @GeneratedValue(strategy=GenerationType.AUTO,generator="userOfPopster_sequence")
    private Long user_id;
    private String typeOfUser;
    private String user_name;
    private String password;
    private String name;
    private String surname;
    private LocalDate Birth;
    private String eMail;
    private String Role;
    @Transient
    private Integer age;


    public UserOfPopster(Long user_id, String user_name, String password, String name, String surname, LocalDate birth, String eMail, String typeOfUser, String role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.name = name;
        this.surname = surname;
        Birth = birth;
        this.eMail = eMail;
        this.typeOfUser = typeOfUser;
        this.Role = role;
    }

    public UserOfPopster() {
    }

    public UserOfPopster(String user_name, String password, String name, String surname, LocalDate birth, String eMail, String typeOfUser, String role) {
        this.user_name = user_name;
        this.password = password;
        this.name = name;
        this.surname = surname;
        Birth = birth;
        this.eMail = eMail;
        this.typeOfUser = typeOfUser;
        this.Role = role;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirth() {
        return Birth;
    }

    public void setBirth(LocalDate birth) {
        Birth = birth;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }



    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserOfPopster that = (UserOfPopster) o;
        return Objects.equals(getUser_id(), that.getUser_id()) && Objects.equals(getTypeOfUser(), that.getTypeOfUser()) && Objects.equals(getUser_name(), that.getUser_name()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getName(), that.getName()) && Objects.equals(getSurname(), that.getSurname()) && Objects.equals(getBirth(), that.getBirth()) && Objects.equals(geteMail(), that.geteMail()) && Objects.equals(getRole(), that.getRole()) && Objects.equals(getAge(), that.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getTypeOfUser(), getUser_name(), getPassword(), getName(), getSurname(), getBirth(), geteMail(), getRole(), getAge());
    }

    @Override
    public String toString() {
        return "UserOfPopster{" +
                "user_id=" + user_id +
                ", typeOfUser='" + typeOfUser + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Birth=" + Birth +
                ", eMail='" + eMail + '\'' +
                ", Role='" + Role + '\'' +
                ", age=" + age +
                '}';
    }
}
