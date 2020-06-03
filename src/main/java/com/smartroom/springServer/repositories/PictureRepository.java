package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Picture;
import org.springframework.data.repository.CrudRepository;

//在SpringMVC中，为我提供了用于进行数据增查删改删的CrudRepository接口
// SpringMVC做自动的为我们的接口做相关的实现。而我们并不需要关心Spring是怎么做到的。
public interface PictureRepository extends CrudRepository<Picture, Long>{
    public Iterable<Picture> findByOwner(String owner);
}
