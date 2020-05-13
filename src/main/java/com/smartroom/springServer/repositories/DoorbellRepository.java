package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Doorbell;
import org.springframework.data.repository.CrudRepository;
public interface DoorbellRepository extends CrudRepository<Doorbell, Long>{
}