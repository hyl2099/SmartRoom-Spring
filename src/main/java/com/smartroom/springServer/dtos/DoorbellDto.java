package com.smartroom.springServer.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smartroom.springServer.documents.Doorbell;
import com.smartroom.springServer.documents.Picture;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoorbellDto {
    private String id;
    private Date ringTime;
    private String picture;

    public DoorbellDto() {
        // Empty for framework
    }

    public DoorbellDto(Doorbell doorbell) {
        this.id = doorbell.getId();
        this.ringTime = doorbell.getRingTime();
        this.picture = doorbell.getPicture();
    }

    public DoorbellDto(String id, Date ringTime, String picture) {
        this.id = id;
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
