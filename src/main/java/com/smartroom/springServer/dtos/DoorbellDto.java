package com.smartroom.springServer.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smartroom.springServer.documents.Doorbell;
import com.smartroom.springServer.documents.Picture;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoorbellDto {
    private Long id;
    private Date ringTime;
    private Picture picture;

    public DoorbellDto() {
        // Empty for framework
    }

    public DoorbellDto(Doorbell doorbell) {
        this.id = doorbell.getId();
        this.ringTime = doorbell.getRingTime();
        this.picture = doorbell.getPicture();
    }

    public DoorbellDto(Long id, Date ringTime, Picture picture) {
        this.id = id;
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

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
