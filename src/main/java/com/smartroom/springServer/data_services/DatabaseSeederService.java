package com.smartroom.springServer.data_services;


import com.smartroom.springServer.documents.*;
import com.smartroom.springServer.repositories.DoorbellRepository;
import com.smartroom.springServer.repositories.PictureRepository;
import com.smartroom.springServer.repositories.VideoRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;

//在SpringMVC中，C层更多在功能是：数据转发，数据验证，数据绑定，路由设定等。而不负责具体的数据的处理。
// 显然的，我们上述代码的C层中，对数据进行逻辑处理。这违背了上述的原则。
// SpringMVC中，service层负责对进行逻辑运算及数据的处理。


//@Service说明本类是一个Service，Spring在进行自动注入的时候，会将有此类注入到相应的TeacherService中。
@Service
public class DatabaseSeederService {

//    @Value("${smartroom.admin.mobile}")
//    private String mobile;
//    @Value("${smartroom.admin.username}")
//    private String username;
//    @Value("${smartroom.admin.password}")
//    private String password;


    private PictureRepository pictureRepository;
    private VideoRepository videoRepository;
    private DoorbellRepository doorbellRepository;

    //自动注入PictureRepository,VideoRepository,DoorbellRepository
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

    public void deleteAllAndInitialize() {
        LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
        // Delete Repositories -----------------------------------------------------
        this.pictureRepository.deleteAll();
        this.videoRepository.deleteAll();
        this.doorbellRepository.deleteAll();
        this.initialize();
    }

    public void seedDataBaseJava() {
        LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");
        Picture[] picture = {
                new Picture("yuling", new Date(),"------------"),
                new Picture("yuling", new Date(),"------------")
        };
        this.pictureRepository.saveAll(Arrays.asList(picture));
        LogManager.getLogger(this.getClass()).warn("        ------- picture");

        Video[] video = {
                new Video("aaa", new Date(),"------------"),
                new Video("bbb", new Date(),"------------")
        };
        this.videoRepository.saveAll(Arrays.asList(video));
        LogManager.getLogger(this.getClass()).warn("        ------- video");

        Doorbell[] doorbell = {
                new Doorbell(new Date(),(long)1),
                new Doorbell(new Date(),(long)2)
        };
        this.doorbellRepository.saveAll(Arrays.asList(doorbell));
        LogManager.getLogger(this.getClass()).warn("        ------- doorbell");
    }
}
