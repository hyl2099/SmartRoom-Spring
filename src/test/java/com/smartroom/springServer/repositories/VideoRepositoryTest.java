package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoRepositoryTest {
    @Autowired
    private VideoRepository videoRepository;

    @Test
    public void addVideo() {
        // 执行数据保存操作
        videoRepository.save(new Video());
        System.out.println(videoRepository);
    }
}
