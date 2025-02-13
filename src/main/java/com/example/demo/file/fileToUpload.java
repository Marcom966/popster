package com.example.demo.file;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Arrays;

@Entity
@Table(name = "fileToUpload")
public class fileToUpload {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    private String fileName;
    private String type;

    public String getUsernName() {
        return usernName;
    }

    public void setUsernName(String usernName) {
        this.usernName = usernName;
    }

    private String usernName;
    @Basic
    @Column(name = "file_size", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] fileSize;

    public fileToUpload(String id, String userName, String fileName, String type, byte[] fileSize) {
        this.fileName = fileName;
        this.type = type;
        this.fileSize = fileSize;
        this.usernName = userName;
        this.id = id;
    }

    public fileToUpload() {
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getType() {
        return type;
    }

    public byte[] getFileSize() {
        return fileSize;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFileSize(byte[] fileSize) {
        this.fileSize = fileSize;
    }
}
