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

    //因为涉及到图片，没有用到
    public Iterable<Picture> readAll(){
        return this.pictureRepository.findAll();
    }

    //post一个picture，因为涉及到图片，没有用到
    public Picture savePicture(Picture picture){
        return this.pictureRepository.save(picture);
    }


    public Optional<Picture> findPictureById(Long id){
        return this.pictureRepository.findById(id);
    }
    public Iterable<Picture> findPictureByOwner(String owner){
        return this.pictureRepository.findByOwner(owner);
    }

    //这个是patch更新所有字段，不需要传递整个picture对象
    public int updateOwnerOrRemark(Long id, String owner,String remark) throws EntityNotFoundException {
        // 判断是否存在该实体，如果不存在，则报错
        if (pictureRepository.findById(id) == null) {
            throw new EntityNotFoundException("the ID :" + id.toString() + "Wrong, no entity.");

        }
        if(remark == "" && owner !=""){
            this.pictureRepository.updatePictureOwner(owner,id);
            return 1;
        }else if(remark!="" && owner ==""){
            this.pictureRepository.updatePictureRemark(remark,id);
            return 1;
        }else if(remark != "" && owner!= ""){
            this.pictureRepository.updatePictureOwnerAndRemark(owner,remark,id);
            return 1;
        }else {return 0;}
    }

    public void deletePicture(Long id) {
        this.pictureRepository.deleteById(id);
    }


}
