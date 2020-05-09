package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;
public class Doorbell {
    @Id
    @GeneratedValue
    private String id;
    private Date ringTime;

    @ManyToMany
    private Picture picture;

    protected Doorbell() { }

    public Doorbell(Date ringTime,Picture picture) {
        this.ringTime = ringTime;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getRingTime() {
        return ringTime;
    }

    public void setRingTime(Date ringTime) {
        this.ringTime = ringTime;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
