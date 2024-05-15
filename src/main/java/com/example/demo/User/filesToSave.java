package com.example.demo.User;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class filesToSave {
    @Id
    @SequenceGenerator(name="Files To Save", allocationSize = 1, sequenceName = "FilesToSave_sequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator="FilesToSave_sequence")
    private Long Id;
    private Integer size;
    private String name;
    private String type;

    public filesToSave(Long id, Integer size, String name, String type) {
        Id = id;
        this.size = size;
        this.name = name;
        this.type = type;
    }

    public filesToSave() {
    }

    public filesToSave(Integer size, String name, String type) {
        this.size = size;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof filesToSave that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSize(), that.getSize()) && Objects.equals(getName(), that.getName()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSize(), getName(), getType());
    }

    @Override
    public String toString() {
        return "filesToSave{" +
                "Id=" + Id +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
