package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.business_controllers.PictureController;
import com.smartroom.springServer.business_controllers.PictureUploadController;
import com.smartroom.springServer.documents.Picture;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;

// 声明为Rest控制器（支持前后台分离)
@RestController
public class PictureResource {
    public static final String PICTURES = "/pictures";
    public static final String ID = "/{id}";

    private String IMAGEPATH = "D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures";
    private String imagePath;
    private PictureController pictureController;
    private PictureUploadController pictureUploadController;

    @Autowired
    ServletContext context;
    @Autowired
    public PictureResource(PictureController pictureController,PictureUploadController pictureUploadController) {
        this.pictureController = pictureController;
        this.pictureUploadController = pictureUploadController;
    }

    // 设置路由
    // 使用@RequestBody注解，将请求的`json`数据，直接加载至Picture对象
    // 因为有图片，实际没有用到
    @RequestMapping("/pictures/save")
    public Picture savePicture(@RequestBody Picture picture) {
        // 打印加载的数据
        System.out.println(picture);
        // 调用保存操作
        return pictureController.savePicture(picture);
    }

    // @GetMapping 表明该方法只接收 get 请求.
    //根据图片Id返回图片的所有信息。
    @GetMapping("/pictures/id/{id}")
    public ResponseEntity<Picture> findPictureById(@PathVariable Long id) throws IOException{
        Picture picture;
        picture = this.pictureController.findPictureById(id).get();
        String filepath = picture.getPath();
        String fileFolderPath = "D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures";
        File file = new File(fileFolderPath + filepath);
        if(!file.isDirectory()){
            String encodeBase64 = null;
            try{
                String extension = FilenameUtils.getExtension(file.getName());
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fileInputStream.read(bytes);
                encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                picture.setImage("data:image/"+extension+";base64,"+encodeBase64);
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity(picture, HttpStatus.OK);
    }

    // 根据owner返回图片信息的List
    @GetMapping("/pictures/owner/{owner}")
    public ResponseEntity<List<Picture>> findPictureById(@PathVariable String owner) throws IOException{
        List<Picture> pictures = new ArrayList<Picture>();
        List<Long> ids = new ArrayList<Long>();
        Iterable<Picture> picturesFromDB = this.pictureController.findPictureByOwner(owner);
        for(final Picture p : picturesFromDB){
            ids.add(p.getId());
        }
        String fileFolderPath = "D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures";
        for(final Long id : ids){
            String filepath = this.pictureController.findPictureById(id).get().getPath();
            File file = new File(fileFolderPath + filepath);
            if(!file.isDirectory()){
                String encodeBase64 = null;
                String image = null;
                try{
                    String extension = FilenameUtils.getExtension(file.getName());
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] bytes = new byte[(int)file.length()];
                    fileInputStream.read(bytes);
                    encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                    image = "data:image/"+extension+";base64,"+encodeBase64;
                    fileInputStream.close();
                    Picture returnPicture = new Picture();
                    returnPicture = this.pictureController.findPictureById(id).get();
                    returnPicture.setImage(image);
                    pictures.add(returnPicture);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<List<Picture>>(pictures, HttpStatus.OK);
    }


    // @PutMapping 表明该方法只接收 put 请求.
    @PatchMapping("/pictures/update/{id}")
    public int updatePicture(@PathVariable Long id,@RequestBody Picture picture){
        return this.pictureController.updateOwnerOrRemark(picture.getId(),picture.getRemark(),picture.getOwner());
    }
    //删除一个图片，目前仅删除数据库，没有删除图片文件
    @DeleteMapping("/pictures/delete/{id}")
    public void deletePicture(@PathVariable Long id) {
        this.pictureController.deletePicture(id);
    }


    //获取全部图片全部信息。
    @GetMapping("/pictures")
    public ResponseEntity<List<Picture>> getPictures() throws IOException {
        List<Picture> pictures = new ArrayList<Picture>();
        List<Long> ids = new ArrayList<Long>();
        Iterable<Picture> picturesFromDB = this.pictureController.readAll();
        for(final Picture p : picturesFromDB){
            ids.add(p.getId());
        }
        String fileFolderPath = "D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures";
        for(final Long id : ids){
            String filepath = this.pictureController.findPictureById(id).get().getPath();
            File file = new File(fileFolderPath + filepath);
            if(!file.isDirectory()){
                String encodeBase64 = null;
                String image = null;
                try{
                    String extension = FilenameUtils.getExtension(file.getName());
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] bytes = new byte[(int)file.length()];
                    fileInputStream.read(bytes);
                    encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                    image = "data:image/"+extension+";base64,"+encodeBase64;
                    fileInputStream.close();
                    Picture returnPicture = new Picture();
                    returnPicture = this.pictureController.findPictureById(id).get();
                    returnPicture.setImage(image);
                    pictures.add(returnPicture);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<List<Picture>>(pictures, HttpStatus.OK);
    }
    //笔记：@PathVariable 和 @RequestParam差别
    //@PathVariable is used to tell Spring that part of the URI path is a value you want passed to your method.
    //If you want @PathVariable, you need to specify placeholders in the @RequestMapping entry to tell Spring where the path variables fit in the URI.
    // For example, if you want to extract a path variable called contentId, you would use:
    //@RequestMapping(value = "/whatever/{contentId}", method = RequestMethod.POST)
    //If you want form data, use @RequestParam instead of @PathVariable.
    @PostMapping("/pictures/addPhoto")
    @ResponseBody
    public Picture AddPhotoFromAndroid(@RequestParam("file") MultipartFile file,
                                       @RequestParam String owner,
                                       @RequestParam String remark
                                       ) throws IOException {
        if(file.isEmpty()){
            System.out.println("file empty!");
        }
        //使用时间给上传的文件命名，这种方式没有用uuid命名好，因为同一时间有可能会上传多个文件
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        //获取文件的后缀名
        String newFileName = res+".jpg";
        // 跟目录设置是在SpringServerApplication 中的MultipartConfigElement中设置的。
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
        Picture picture = new Picture();
        picture.setOwner(owner);
        picture.setUploadTime(new Date());
        picture.setRemark(remark);
        return pictureUploadController.add(src,picture);
    }


}
