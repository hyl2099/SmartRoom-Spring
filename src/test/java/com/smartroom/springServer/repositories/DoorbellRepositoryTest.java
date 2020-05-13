package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Doorbell;
import com.smartroom.springServer.documents.Picture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoorbellRepositoryTest {
    @Autowired
    private DoorbellRepository doorbellRepository;

    @Test
    public void addDoorbell() {
        // 执行数据保存操作
        doorbellRepository.save(new Doorbell(new Date(),(long)1));
        System.out.println(doorbellRepository);
    }
}
