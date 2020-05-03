package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;
public class Video {
    @Id
    @GeneratedValue
    private Long id;
    private String owner;
    private Date uploadTime;

    protected Video() { }

    public Video(String owner, Date uploadTime) {
        this.owner = owner;
        this.uploadTime = uploadTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
