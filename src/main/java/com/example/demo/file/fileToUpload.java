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
    private String id;
    private String fileName;
    private String type;
    private String artistName;
    private String songName;
    private String usernName;
    @Basic
    @Column(name = "file_size", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] fileSize;

    public fileToUpload(String id, String userName, String fileName, String type, byte[] fileSize, String artistName, String songName) {
        this.fileName = fileName;
        this.type = type;
        this.fileSize = fileSize;
        this.usernName = userName;
        this.id = id;
        this.artistName = artistName;
        this.songName = songName;
    }

    public fileToUpload() {
    }

    public String getUsernName() {
        return usernName;
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

    public String getArtistName(){return  artistName;};

    public String getSongName(){return  songName;};

    public void setArtistName(String artistName){this.artistName = artistName;};

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

    public void setSongName(String songName){this.songName = songName;};

    public void setUsernName(String usernName) {
        this.usernName = usernName;
    }
}
