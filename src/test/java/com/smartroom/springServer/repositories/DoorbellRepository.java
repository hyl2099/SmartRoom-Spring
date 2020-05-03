package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Doorbell;
import com.smartroom.springServer.documents.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
public interface DoorbellRepository extends CrudRepository<Doorbell, Long>{
    Picture findDoorbellById(String id);
}