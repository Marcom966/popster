package com.example.demo.file;

public class fileResponse {
    private String name;
    private String url;
    private String type;
    private String id;
    private String usernName;
    private long size;

    public fileResponse(String name, long size, String url, String type, String id, String usernName) {
        this.name = name;
        this.size = size;
        this.url = url;
        this.id = id;
        this.usernName = usernName;
        this.type = type;
    }

    public fileResponse(String fileName, int length, String type, String id, String usernName, Object o, String fileDownloadUri) {
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
