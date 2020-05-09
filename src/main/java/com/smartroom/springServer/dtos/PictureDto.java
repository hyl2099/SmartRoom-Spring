package com.smartroom.springServer.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smartroom.springServer.documents.Picture;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PictureDto {
    private String id;
    private String owner;
    private Date uploadTime;

    public PictureDto() {
    }

    public PictureDto(Picture picture) {
        this.id = picture.getId();
        this.owner = picture.getOwner();
        this.uploadTime = picture.getUploadTime();
    }

    public PictureDto(String id, String owner, Date uploadTime) {
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