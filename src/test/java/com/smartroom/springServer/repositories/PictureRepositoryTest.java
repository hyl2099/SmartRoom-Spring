package com.smartroom.springServer.repositories;
import com.smartroom.springServer.documents.Picture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// 使用以下两个注解来说明：本测试类基于SpringBoot。(必须)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PictureRepositoryTest {
    // @Autowired注解：自动加载Spring为我们自动实例化的实现了TeacherRepository接口的对象
    @Autowired
    private PictureRepository pictureRepository;

    // @Test：本方法为一个单元测试方法
    @Test
    public void addPicture() {
        // 执行数据保存操作
        pictureRepository.save(new Picture("Yuling", new Date(), "C:picture.jpg",null));

        // 打印Spring为我们自动实例化的对象
        System.out.println(pictureRepository);
    }
}
