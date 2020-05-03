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
}
