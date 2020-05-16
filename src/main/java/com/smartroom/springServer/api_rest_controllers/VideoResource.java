package com.smartroom.springServer.api_rest_controllers;


import com.smartroom.springServer.business_controllers.VideoController;
import com.smartroom.springServer.documents.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VideoResource {
    private VideoController videoController;

    @Autowired
    public VideoResource(VideoController videoController) {
        this.videoController = videoController;
    }

    // 设置路由
    @RequestMapping("/videos/save")
    public Video saveVideo(@RequestBody Video video) {
        return videoController.saveVideo(video);
    }

    @GetMapping("/videos/all")
    public Iterable<Video> readAll(){
        return videoController.readAll();
    }

    // @GetMapping 表明该方法只接收 get 请求.
    @GetMapping("/videos/{id}")
    public Optional<Video> findVideoById(@PathVariable Long id){
        return videoController.findVideoById(id);
    }

    // @PutMapping 表明该方法只接收 put 请求.
    @PutMapping("/videos/{id}")
    public Video updateVideo(@PathVariable Long id,@RequestBody Video Video){
        return this.videoController.updateVideo(id, Video);
    }

    @DeleteMapping("videos/{id}")
    public void deleteVideo(@PathVariable Long id) {
        this.videoController.deleteVideo(id);
    }
}
