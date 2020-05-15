package com.smartroom.springServer.data_services;


import com.smartroom.springServer.documents.Picture;
import com.smartroom.springServer.repositories.DoorbellRepository;
import com.smartroom.springServer.repositories.PictureRepository;
import com.smartroom.springServer.repositories.VideoRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Service
public class DatabaseSeederService {

    private PictureRepository pictureRepository;
    private VideoRepository videoRepository;
    private DoorbellRepository doorbellRepository;

    @Autowired
    public DatabaseSeederService(PictureRepository pictureRepository,VideoRepository videoRepository,DoorbellRepository doorbellRepository){
        this.pictureRepository = pictureRepository;
        this.videoRepository = videoRepository;
        this.doorbellRepository = doorbellRepository;
    }

    @PostConstruct
    public void constructor() {
        this.initialize();
    }


    private void initialize() {
        this.seedDataBaseJava();
    }

    public void seedDataBaseJava() {
        LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");
        Picture[] picture = {
                new Picture("yuling", new Date(),"------------"),
                new Picture("yuling", new Date(),"------------")
        };
        this.pictureRepository.saveAll(Arrays.asList(picture));
        LogManager.getLogger(this.getClass()).warn("        ------- picture");
    }
}
