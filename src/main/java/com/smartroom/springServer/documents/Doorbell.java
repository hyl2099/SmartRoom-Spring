package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Doorbell {
    @Id
    @GeneratedValue
    private String id;
    private Date ringTime;

    private String picture;

    protected Doorbell() { }

    public Doorbell(Date ringTime,String picture) {
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
