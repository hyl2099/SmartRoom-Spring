package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.business_controllers.DoorbellController;
import com.smartroom.springServer.documents.Doorbell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DoorbellResource {
    private DoorbellController doorbellController;

    @Autowired
    public DoorbellResource(DoorbellController doorbellController) {
        this.doorbellController = doorbellController;
    }

    // 设置路由
    @RequestMapping("/doorbells/save")
    // 使用@RequestBody注解，将请求的`json`数据，直接加载至doorbell对象
    public Doorbell savedoorbell(@RequestBody Doorbell doorbell) {
        // 打印加载的数据
        System.out.println(doorbell);

        // 调用保存操作
        return doorbellController.saveDoorbell(doorbell);
    }

    @GetMapping("/doorbells/all")
    public Iterable<Doorbell> readAll(){
        return doorbellController.readAll();
    }

    // @GetMapping 表明该方法只接收 get 请求.
    @GetMapping("/doorbells/{id}")
    public Optional<Doorbell> finddoorbellById(@PathVariable Long id){
        return doorbellController.findDoorbellById(id);
    }

    // @PutMapping 表明该方法只接收 put 请求.
    @PutMapping("/doorbells/{id}")
    public Doorbell updatedoorbell(@PathVariable Long id,@RequestBody Doorbell doorbell){
        return this.doorbellController.updateDoorbell(id, doorbell);
    }

    @DeleteMapping("doorbells/{id}")
    public void deletedoorbell(@PathVariable Long id) {
        this.doorbellController.deleteDoorbell(id);
    }
}
