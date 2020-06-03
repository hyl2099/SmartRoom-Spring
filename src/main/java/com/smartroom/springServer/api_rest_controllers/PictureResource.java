package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.business_controllers.PictureController;
import com.smartroom.springServer.documents.Picture;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

// 声明为Rest控制器（支持前后台分离)
@RestController
public class PictureResource {
    public static final String PICTURES = "/pictures";
    public static final String ID = "/{id}";

    private String IMAGEPATH = "D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures";
    private String imagePath;
    private PictureController pictureController;

    @Autowired
    ServletContext context;
    @Autowired
    public PictureResource(PictureController pictureController) {
        this.pictureController = pictureController;
    }

    // 设置路由
    @RequestMapping("/pictures/save")
    // 使用@RequestBody注解，将请求的`json`数据，直接加载至Picture对象
    public Picture savePicture(@RequestBody Picture picture) {
        // 打印加载的数据
        System.out.println(picture);

        // 调用保存操作
        return pictureController.savePicture(picture);
    }

    // @GetMapping 表明该方法只接收 get 请求.
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

    @DeleteMapping("/pictures/delete/{id}")
    public void deletePicture(@PathVariable Long id) {
        this.pictureController.deletePicture(id);
    }


//    // 根据ID 返回仅仅图片文件
//    @GetMapping("/picturesphoto/{id}")
//    public ResponseEntity<String> getPhotoById (@PathVariable Long id) throws IOException{
//        String images = null;
//        String filepath = this.pictureController.findPictureById(id).get().getPath();
//        String fileFolderPath = "D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures";
//        File file = new File(fileFolderPath + filepath);
//        if(!file.isDirectory()){
//            String encodeBase64 = null;
//            try{
//                String extension = FilenameUtils.getExtension(file.getName());
//                FileInputStream fileInputStream = new FileInputStream(file);
//                byte[] bytes = new byte[(int)file.length()];
//                fileInputStream.read(bytes);
//                encodeBase64 = Base64.getEncoder().encodeToString(bytes);
//                images = "data:image/"+extension+";base64,"+encodeBase64;
//                fileInputStream.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return new ResponseEntity(images, HttpStatus.OK);
//    }

    //获取资源文件夹中全部图片的list。
    @GetMapping("/getImages")
    public ResponseEntity<List<String>> getImages() throws IOException {
        List<String> images = new ArrayList<String>();
        String filepath = context.getRealPath("/images");
        File fileFolder = new File(filepath);
        if(fileFolder!=null){
            for(final File file : fileFolder.listFiles()){
                if(!file.isDirectory()){
                    String encodeBase64 = null;
                    try{
                        String extension = FilenameUtils.getExtension(file.getName());
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] bytes = new byte[(int)file.length()];
                        fileInputStream.read(bytes);
                        encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                        images.add("data:image/"+extension+";base64,"+encodeBase64);
                        fileInputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new ResponseEntity<List<String>>(images, HttpStatus.OK);
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

}
