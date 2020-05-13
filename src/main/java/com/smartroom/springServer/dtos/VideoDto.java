package com.smartroom.springServer.dtos;

import com.smartroom.springServer.documents.Video;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDto {
    private Long id;
    private String owner;
    private Date uploadTime;
    private String path;

    public VideoDto() {
    }

    public VideoDto(Video video) {
        this.id = video.getId();
        this.owner = video.getOwner();
        this.uploadTime = video.getUploadTime();
        this.path = path;
    }

    public VideoDto(Long id, String owner, Date uploadTime,String path) {
        this.id = id;
        this.owner = owner;
        this.uploadTime = uploadTime;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
