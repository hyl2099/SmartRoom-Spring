package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Picture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

//在SpringMVC中，为我提供了用于进行数据增查删改删的CrudRepository接口
// SpringMVC做自动的为我们的接口做相关的实现。而我们并不需要关心Spring是怎么做到的。
public interface PictureRepository extends CrudRepository<Picture, Long>{
    public Iterable<Picture> findByOwner(String owner);

    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update Picture p set p.owner = ?1 where p.id = ?2")
    void updatePictureOwner(String owner, Long id);

    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update Picture p set p.remark = ?1 where p.id = ?2")
    void updatePictureRemark(String remark, Long id);

    @Modifying//更新查询
    @Transactional//开启事务
    @Query("update Picture p set p.owner = ?1 , p.remark = ?2 where p.id = ?3")
    void updatePictureOwnerAndRemark(String owner, String remark, Long id);

}
