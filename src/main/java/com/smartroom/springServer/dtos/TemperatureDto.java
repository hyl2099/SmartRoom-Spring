package com.smartroom.springServer.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemperatureDto {
    private float temperatureIndoor;
    private float humidityIndoor;
    private float temperatureOutdoor;
    private float humidityOutdoor;
    private Date time;

    public TemperatureDto(float temperatureIndoor, float humidityIndoor, float temperatureOutdoor, float humidityOutdoor, Date time) {
        this.temperatureIndoor = temperatureIndoor;
        this.humidityIndoor = humidityIndoor;
        this.temperatureOutdoor = temperatureOutdoor;
        this.humidityOutdoor = humidityOutdoor;
        this.time = time;
    }

    public float getTemperatureIndoor() {
        return temperatureIndoor;
    }

    public void setTemperatureIndoor(float temperatureIndoor) {
        this.temperatureIndoor = temperatureIndoor;
    }

    public float getHumidityIndoor() {
        return humidityIndoor;
    }

    public void setHumidityIndoor(float humidityIndoor) {
        this.humidityIndoor = humidityIndoor;
    }

    public float getTemperatureOutdoor() {
        return temperatureOutdoor;
    }

    public void setTemperatureOutdoor(float temperatureOutdoor) {
        this.temperatureOutdoor = temperatureOutdoor;
    }

    public float getHumidityOutdoor() {
        return humidityOutdoor;
    }

    public void setHumidityOutdoor(float humidityOutdoor) {
        this.humidityOutdoor = humidityOutdoor;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
