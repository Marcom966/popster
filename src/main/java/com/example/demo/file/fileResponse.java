package com.example.demo.file;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

public class fileResponse {
    private String name;
    private String url;
    private String type;
    private String id;
    private String usernName;
    private String artistName;
    private String songName;
    private long size;
    @Basic
    @Column(name = "file_size", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] data;


    public fileResponse(String name, long size, String url, String type, String id, String usernName, byte[] data, String artistName, String songName) {
        this.name = name;
        this.size = size;
        this.url = url;
        this.id = id;
        this.usernName = usernName;
        this.type = type;
        this.data = data;
        this.artistName = artistName;
        this.songName = songName;
    }

    public fileResponse(String fileName, int length, String type, String id, String usernName, Object o, String fileDownloadUri, byte[] data, String artistName, String songName) {
    }


    public String getArtistName(){return artistName;};

    public void setArtistName(String artistName){this.artistName = artistName;};

    public String getSongName(){return songName;};

    public void  setSongName(String songName){this.songName = songName;};

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernName() {
        return usernName;
    }

    public void setUsernName(String usernName) {
        this.usernName = usernName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


}
