package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Picture {
    @Id
    @GeneratedValue
    private String id;
    private String owner;
    private Date uploadTime;


    protected Picture() { }

    public Picture(String owner, Date uploadTime) {
        this.owner = owner;
        this.uploadTime = uploadTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
