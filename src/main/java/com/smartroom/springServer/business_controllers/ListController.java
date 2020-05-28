package com.smartroom.springServer.business_controllers;

import com.smartroom.springServer.documents.Picture;
import com.smartroom.springServer.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ListController {
    PictureRepository pictureRepository;

    @Autowired
    public ListController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture add(String src, Picture picture){
        picture.setPath(src);
        return this.pictureRepository.save(picture);
    }
}
