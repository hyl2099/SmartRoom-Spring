package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Picture {
    @Id
    @GeneratedValue
    private Long id;
    private String owner;
    private Date uploadTime;


    protected Picture() { }

    public Picture(String owner, Date uploadTime) {
        this.owner = owner;
        this.uploadTime = uploadTime;
    }
}
