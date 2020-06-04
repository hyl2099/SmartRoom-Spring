package com.smartroom.springServer.api_rest_controllers;


import com.smartroom.springServer.business_controllers.TemperatureController;
import com.smartroom.springServer.documents.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TemperatureResource {
    private TemperatureController temperatureController;

    @Autowired
    public TemperatureResource(TemperatureController videoController) {
        this.temperatureController = videoController;
    }

    // 设置路由
    @RequestMapping("/temperature/save")
    public Temperature saveVideo(@RequestBody Temperature temperature) {
        return temperatureController.saveTemperature(temperature);
    }

    @GetMapping("/temperature/all")
    public Iterable<Temperature> readAll(){
        return temperatureController.readAll();
    }

}
