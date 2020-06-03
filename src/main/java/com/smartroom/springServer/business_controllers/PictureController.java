package com.smartroom.springServer.business_controllers;

import com.smartroom.springServer.business_services.PhotoService;
import com.smartroom.springServer.documents.Picture;
import com.smartroom.springServer.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Controller
public class PictureController {
    PictureRepository pictureRepository;
    private PhotoService photoService;

    //自动装入pictureRepository
    @Autowired
    public PictureController(PictureRepository pictureRepository, PhotoService photoService) {
        this.pictureRepository = pictureRepository;
        this.photoService = photoService;
    }

    public Iterable<Picture> readAll(){
        return this.pictureRepository.findAll();
    }

    public Picture savePicture(Picture picture){
        return this.pictureRepository.save(picture);
    }

    public Optional<Picture> findPictureById(Long id){
        return this.pictureRepository.findById(id);
    }
    public Iterable<Picture> findPictureByOwner(String owner){
        return this.pictureRepository.findByOwner(owner);
    }


    public Picture updatePicture(Long id, Picture picture) throws EntityNotFoundException {
        // 判断是否存在该实体，如果不存在，则报错
        if (pictureRepository.findById(id) == null) {
            throw new EntityNotFoundException("the ID :" + id.toString() + "Wrong, no entity.");
        }
        // 对实体ID赋值, 并执行更新操作
        picture.setId(id);
        return this.pictureRepository.save(picture);
    }

    public void deletePicture(Long id) {
        this.pictureRepository.deleteById(id);
    }

    //注解管理事务的实现步骤
    @Transactional
    public Optional<byte[]> getPhoto(Long id) {
        return pictureRepository.findById(id).map(picture -> photoService.readPhoto(picture.getPath()));
    }

}
