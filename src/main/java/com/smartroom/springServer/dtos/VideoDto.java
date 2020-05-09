package com.smartroom.springServer.dtos;

import com.smartroom.springServer.documents.Video;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDto {
    private String id;
    private String owner;
    private Date uploadTime;

    public VideoDto() {
    }

    public VideoDto(Video video) {
        this.id = video.getId();
        this.owner = video.getOwner();
        this.uploadTime = video.getUploadTime();
    }

    public VideoDto(String id, String owner, Date uploadTime) {
        this.id = id;
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
