package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.business_controllers.PictureController;
import com.smartroom.springServer.documents.Picture;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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

    @GetMapping("/pictures/all")
    public Iterable<Picture> readAll(){
        return pictureController.readAll();
    }

    // @GetMapping 表明该方法只接收 get 请求.
    @GetMapping("/pictures/{id}")
    public Optional<Picture> findPictureById(@PathVariable Long id){
        return pictureController.findPictureById(id);
    }

    // @PutMapping 表明该方法只接收 put 请求.
    @PutMapping("/pictures/{id}")
    public Picture updatePicture(@PathVariable Long id,@RequestBody Picture picture){
        return this.pictureController.updatePicture(id, picture);
    }

    @DeleteMapping("/pictures/{id}")
    public void deletePicture(@PathVariable Long id) {
        this.pictureController.deletePicture(id);
    }

    //下载图片API
//    @GetMapping("picturesphoto/{id}")
//    public Optional<byte[]> getPhoto(@PathVariable long id) {
//        return this.pictureController.getPhoto(id);
//    }

//    @GetMapping("/picturesphoto/{id}")
    public byte[] getPhotoById (@PathVariable String id) throws IOException{
//        Optional<Picture> entity = this.pictureController.findPictureById(Long. parseLong(id));
//        byte[] data = entity.get().getPhoto();
//        return data;
////        if (width != 0 && height != 0) {
////            data = scaleImage(data, width, height);
////        }
////        response.setContentType("image/jpeg");
////        response.setCharacterEncoding("UTF-8");
////        OutputStream outputSream = response.getOutputStream();
//        InputStream in = new ByteArrayInputStream(data);
//        int len = 0;
//        byte[] buf = new byte[1024];
//        while ((len = in.read(buf, 0, 1024)) != -1) {
////            outputSream.write(buf, 0, len);
//        }
////        outputSream.close();
//        return Files.readAllBytes(Paths.get("D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures\\pictures\\1.jpg"));


//        this.imagePath = entity.get().getPath();
//        ClassPathResource resource = new ClassPathResource(this.IMAGEPATH+this.imagePath);
        ClassPathResource resource = new ClassPathResource("D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures\\1.jpg");
        byte[] buffer = null;
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(resource.getFile());
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) != -1)
            {
                output.write(b, 0, length);
            }
            buffer = output.toByteArray();
            output.close();
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buffer;
    }
//
//    /**
//   * 显示缩微图
//   * TODO
//   * @param data
//   * @param width
//   * @param height
//   * @return
//   * @throws IOException<br/>
//   * ============History===========<br/>
//   */
//    public static byte[] scaleImage(byte[] data, int width, int height) throws IOException {
//        BufferedImage buffered_oldImage = ImageIO.read(new ByteArrayInputStream(data));
//        int imageOldWidth = buffered_oldImage.getWidth();
//        int imageOldHeight = buffered_oldImage.getHeight();
//        double scale_x = (double) width / imageOldWidth;
//        double scale_y = (double) height / imageOldHeight;
//    double scale_xy = Math.min(scale_x, scale_y);
//    int imageNewWidth = (int) (imageOldWidth * scale_xy);
//    int imageNewHeight = (int) (imageOldHeight * scale_xy);
//    BufferedImage buffered_newImage = new BufferedImage(imageNewWidth, imageNewHeight, BufferedImage.TYPE_INT_RGB);
//    buffered_newImage.getGraphics().drawImage(buffered_oldImage.getScaledInstance(imageNewWidth, imageNewHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);
//    buffered_newImage.getGraphics().dispose();
//    ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
//        ImageIO.write(buffered_newImage, "jpeg", outPutStream);
//        return outPutStream.toByteArray();
//    }


    @GetMapping("/getImages")
    public ResponseEntity<List<String>> getImages() throws IOException {
        List<String> images = new ArrayList<String>();
        // 在使用ServletContext.getRealPath() 时，传入的参数是从 当前servlet 部署在tomcat中的文件夹算起的相对路径，要以"/" 开头，否则会找不到路径，导致NullPointerException
        // 此处是 从src/webapp开始
        //String filepath = context.getRealPath("/images");
        String filepath = "D:\\UPM_MASTER_MIW\\mater_MIW_UPM\\10-TFM\\SmartRoom-Pictures\\pictures";
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

}
