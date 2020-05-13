package com.smartroom.springServer.business_controllers;

import com.smartroom.springServer.documents.Picture;
import com.smartroom.springServer.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class PictureController {
    PictureRepository pictureRepository;

    //自动装入pictureRepository
    @Autowired
    public PictureController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Flux<Picture> readAll() {
        return (Flux<Picture>) this.pictureRepository.findAll();
    }

    public Picture savePicture(Picture picture){
        return this.pictureRepository.save(picture);
    }

}
