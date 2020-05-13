package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.business_controllers.PictureController;
import com.smartroom.springServer.documents.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

// 声明为Rest控制器（支持前后台分离)
@RestController
public class PictureResource {
    public static final String PICTURES = "/pictures";
    public static final String ID = "/{id}";

    private PictureController pictureController;

    @Autowired
    public PictureResource(PictureController pictureController) {
        this.pictureController = pictureController;
    }

    // 设置路由
    @RequestMapping("/pictures/save")
    // 使用@RequestBody注解，将请求的`json`数据，直接加载至teacher对象
    public Picture savePicture(@RequestBody Picture picture) {
        // 打印加载的数据
        System.out.println(picture);

        // 调用保存操作
        return pictureController.savePicture(picture);
    }

    @GetMapping
    public Flux<Picture> readAll(){
        return pictureController.readAll();
    }
}
