package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.business_controllers.PictureController;
import com.smartroom.springServer.documents.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

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
    // 使用@RequestBody注解，将请求的`json`数据，直接加载至Picture对象
    public Picture savePicture(@RequestBody Picture picture) {
        // 打印加载的数据
        System.out.println(picture);

        // 调用保存操作
        return pictureController.savePicture(picture);
    }

    @GetMapping("/pictures/all")
    public Iterable<Picture> readAll(){
        return pictureController.readAll();
    }

    // @GetMapping 表明该方法只接收 get 请求.
    @GetMapping("/pictures/{id}")
    public Optional<Picture> findPictureById(@PathVariable Long id){
        return pictureController.findPictureById(id);
    }

    // @PutMapping 表明该方法只接收 put 请求.
    @PutMapping("/pictures/{id}")
    public Picture updatePicture(@PathVariable Long id,@RequestBody Picture picture){
        return this.pictureController.updatePicture(id, picture);
    }

    @DeleteMapping("pictures/{id}")
    public void deletePicture(@PathVariable Long id) {
        this.pictureController.deletePicture(id);
    }

    //下载图片API
    @GetMapping("picturesphoto/{id}")
    public Optional<byte[]> getPhoto(@PathVariable long id) {
        return this.pictureController.getPhoto(id);
    }

}
