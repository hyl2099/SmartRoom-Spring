package com.smartroom.springServer.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smartroom.springServer.documents.Picture;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PictureDto {
    private Long id;
    private String owner;
    private Date uploadTime;
    private String path;
    private byte[] photo;
    private String remark;


    public PictureDto() {
    }

    public PictureDto(Picture picture) {
        this.id = picture.getId();
        this.owner = picture.getOwner();
        this.uploadTime = picture.getUploadTime();
        this.path = path;
    }

    public PictureDto(Long id, String owner, Date uploadTime, String path) {
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
