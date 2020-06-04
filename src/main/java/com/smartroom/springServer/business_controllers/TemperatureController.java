package com.smartroom.springServer.business_controllers;

import com.smartroom.springServer.documents.Temperature;
import com.smartroom.springServer.repositories.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class TemperatureController {
    TemperatureRepository temperatureRepository;

    //自动装入VideoRepository
    @Autowired
    public TemperatureController(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }
    public Iterable<Temperature> readAll() {
        return this.temperatureRepository.findAll();
    }

    public Temperature saveTemperature(Temperature t){
        return this.temperatureRepository.save(t);
    }

    public void deleteTemperature (Long id){
        this.temperatureRepository.deleteById(id);
    }

}
