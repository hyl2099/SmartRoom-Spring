package com.smartroom.springServer.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemperatureDto {
    private Long id;
    private float temperature;
    private float humidity;
    private Date time;

    public TemperatureDto(TemperatureDto temperatureDto) {
        this.id = temperatureDto.id;
        this.temperature = temperatureDto.temperature;
        this.humidity = temperatureDto.humidity;
        this.time = temperatureDto.time;
    }

    public TemperatureDto(float temperature, float humidity, Date time) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
