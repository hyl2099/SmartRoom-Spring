package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;
public class Doorbell {
    @Id
    @GeneratedValue
    private Long id;
    private Date ringTime;

    @ManyToMany
    private Picture picture;

    protected Doorbell() { }

    public Doorbell(Date ringTime,Picture picture) {
        this.ringTime = ringTime;
        this.picture = picture;
    }
}
