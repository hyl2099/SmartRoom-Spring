package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Temperature {
    @Id
    @GeneratedValue
    private Long id;
    private float temperature;
    private float humidity;
    private Date time;

    public Temperature() {
    }

    public Temperature(Temperature t) {
        this.temperature = t.temperature;
        this.humidity = t.humidity;
        this.time = t.time;
    }

    public Temperature(float temperature, float humidity, Date time) {
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
