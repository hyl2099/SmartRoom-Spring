package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Temperature {
    @Id
    @GeneratedValue
    private Long id;
    private float temperatureIndoor;
    private float humidityIndoor;
    private float temperatureOutdoor;
    private float humidityOutdoor;
    private Date time;

    public Temperature() {
    }

    public Temperature(Temperature t) {
        this.temperatureIndoor = t.temperatureIndoor;
        this.humidityIndoor = t.humidityIndoor;
        this.temperatureOutdoor = t.temperatureOutdoor;
        this.humidityOutdoor = t.humidityOutdoor;
        this.time = t.time;
    }

    public Temperature(float temperatureIndoor, float humidityIndoor, float temperatureOutdoor, float humidityOutdoor, Date time) {
        this.temperatureIndoor = temperatureIndoor;
        this.humidityIndoor = humidityIndoor;
        this.temperatureOutdoor = temperatureOutdoor;
        this.humidityOutdoor = humidityOutdoor;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
