package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.business_controllers.ListController;
import com.smartroom.springServer.documents.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/pictures")
public class ListResource {
    private ListController listController;

    @Autowired
    public ListResource(ListController listController) {
        this.listController = listController;
    }


    /**
     * 新建页面添加数据
     * 使用MultipartFile接口接收前台传的file（文件），其他的参数用实体类接收就可以了
     * 前台传到controller中的附件要以MultipartFile类型
     */
    @PostMapping("/addfile")
    @ResponseBody
    public Picture addFile(@RequestParam("file") MultipartFile file, Picture picture) throws IOException {
        if(file.isEmpty()){
            System.out.println("file为空");
        }
        //使用时间给上传的文件命名，这种方式没有用uuid命名好，因为同一时间有可能会上传多个文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        String originaFilename = file.getOriginalFilename();
        //获取文件的后缀名
        String newFileName = res+originaFilename.substring(originaFilename.lastIndexOf("."));
        String rootPath = "/pictures/";
        File newFile = new File(rootPath+newFileName);
        System.out.println(rootPath+newFileName);
        //定义向数据库中存取的文件路径
        String src=rootPath+newFileName;
        if(!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdirs();
        }else{
            System.out.println(newFile.getParentFile());
        }
        if(!newFile.exists()){
            //使用transferTo()方法将文件存到所在服务器上
            file.transferTo(newFile);
        }
        picture.setPhoto(Files.readAllBytes(Paths.get("D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures"+rootPath+newFileName)));
        return listController.add(src,picture);
    }
}