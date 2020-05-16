package com.smartroom.springServer.business_controllers;

import com.smartroom.springServer.documents.Doorbell;
import com.smartroom.springServer.repositories.DoorbellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Controller
public class DoorbellController {
    DoorbellRepository DoorbellRepository;

    //自动装入DoorbellRepository
    @Autowired
    public DoorbellController(DoorbellRepository DoorbellRepository) {
        this.DoorbellRepository = DoorbellRepository;
    }

    public Iterable<Doorbell> readAll() {
        return this.DoorbellRepository.findAll();
    }

    public Doorbell saveDoorbell(Doorbell Doorbell){
        return this.DoorbellRepository.save(Doorbell);
    }

    public Optional<Doorbell> findDoorbellById(Long id){
        return this.DoorbellRepository.findById(id);
    }

    public Doorbell updateDoorbell(Long id, Doorbell Doorbell) throws EntityNotFoundException {
        // 判断是否存在该实体，如果不存在，则报错
        if (DoorbellRepository.findById(id) == null) {
            throw new EntityNotFoundException("the ID :" + id.toString() + "Wrong, no entity.");
        }
        // 对实体ID赋值, 并执行更新操作
        Doorbell.setId(id);
        return this.DoorbellRepository.save(Doorbell);
    }

    public void deleteDoorbell(Long id) {
        this.DoorbellRepository.deleteById(id);
    }
}
