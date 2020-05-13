package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Doorbell {
    @Id
    @GeneratedValue
    private Long id;
    private Date ringTime;

    private Long picture;

    protected Doorbell() { }

    public Doorbell(Date ringTime,Long picture) {
        this.ringTime = ringTime;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRingTime() {
        return ringTime;
    }

    public void setRingTime(Date ringTime) {
        this.ringTime = ringTime;
    }

    public Long getPicture() {
        return picture;
    }

    public void setPicture(Long picture) {
        this.picture = picture;
    }
}
