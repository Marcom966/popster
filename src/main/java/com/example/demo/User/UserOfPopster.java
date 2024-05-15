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
    @Transient
    private Integer age;


    public UserOfPopster(Long user_id, String user_name, String password, String name, String surname, LocalDate birth, String eMail, String typeOfUser) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.name = name;
        this.surname = surname;
        Birth = birth;
        this.eMail = eMail;
        this.typeOfUser = typeOfUser;
    }

    public UserOfPopster() {
    }

    public UserOfPopster(String user_name, String password, String name, String surname, LocalDate birth, String eMail, String typeOfUser) {
        this.user_name = user_name;
        this.password = password;
        this.name = name;
        this.surname = surname;
        Birth = birth;
        this.eMail = eMail;
        this.typeOfUser = typeOfUser;
    }

    public String getTypeOfUser(){
        return typeOfUser;
    }
    public Integer getAge() {
        return Period.between(this.Birth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public void setTypeOfUser(String typeOfUser){
        this.typeOfUser = typeOfUser;
    }

    public LocalDate getBirth() {
        return Birth;
    }

    public void setBirth(LocalDate birth) {
        Birth = birth;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOfPopster userOfPopster)) return false;
        return Objects.equals(getUser_id(), userOfPopster.getUser_id()) && Objects.equals(getUser_name(), userOfPopster.getUser_name()) && Objects.equals(getPassword(), userOfPopster.getPassword()) && Objects.equals(getName(), userOfPopster.getName()) && Objects.equals(getSurname(), userOfPopster.getSurname()) && Objects.equals(getBirth(), userOfPopster.getBirth()) && Objects.equals(geteMail(), userOfPopster.geteMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getUser_name(), getPassword(), getName(), getSurname(), getBirth(), geteMail());
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Birth=" + Birth +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
