package com.example.demo.file;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Entity
@Table
public class fileToUpload {
    @Id
    @SequenceGenerator(name="fileToUpload_sequence", allocationSize = 1, sequenceName = "fileToUpload_sequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "fileToUpload_sequence")
    private String id;
    private String fileName;
    private String type;
    @Lob
    private byte[] fileSize;

    public fileToUpload(String fileName, String type, byte[] fileSize) {
        this.fileName = fileName;
        this.type = type;
        this.fileSize = fileSize;
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
