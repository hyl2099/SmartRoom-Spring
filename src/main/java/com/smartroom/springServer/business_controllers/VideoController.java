package com.smartroom.springServer.business_controllers;

import com.smartroom.springServer.documents.Video;
import com.smartroom.springServer.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Controller
public class VideoController {
    VideoRepository videoRepository;

    //自动装入VideoRepository
    @Autowired
    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Iterable<Video> readAll() {
        return this.videoRepository.findAll();
    }

    public Video saveVideo(Video Video){
        return this.videoRepository.save(Video);
    }

    public Optional<Video> findVideoById(Long id){
        return this.videoRepository.findById(id);
    }

    public Video updateVideo(Long id, Video Video) throws EntityNotFoundException {
        // 判断是否存在该实体，如果不存在，则报错
        if (videoRepository.findById(id) == null) {
            throw new EntityNotFoundException("the ID :" + id.toString() + "Wrong, no entity.");
        }
        // 对实体ID赋值, 并执行更新操作
        Video.setId(id);
        return this.videoRepository.save(Video);
    }

    public void deleteVideo(Long id) {
        this.videoRepository.deleteById(id);
    }
}
