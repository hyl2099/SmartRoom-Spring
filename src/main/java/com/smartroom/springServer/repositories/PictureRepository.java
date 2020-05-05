package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
public interface PictureRepository extends CrudRepository<Picture, Long>{
    Picture findPictureById(String id);
}
