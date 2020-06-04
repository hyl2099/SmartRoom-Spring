package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Temperature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TemperatureRepositoryTest {
    @Autowired
    private TemperatureRepository temperatureRepository;

    @Test
    public void addVideo() {
        // 执行数据保存操作
        temperatureRepository.save(new Temperature(12,34,new Date()));
        System.out.println(temperatureRepository);
    }
}
