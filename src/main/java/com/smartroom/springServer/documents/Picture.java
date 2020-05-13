package com.smartroom.springServer.documents;

import javax.persistence.*;
import java.util.Date;

// 使用@Entity 来说明该类对应一个数据表

@Entity
public class Picture {
    //    声明主键
    @Id
    //    声明主键生成策略为 自动
    @GeneratedValue
    private Long id;
    @Column(length = 25)            // 声明字段的长度为25
    private String owner;
    private Date uploadTime;
    private String path;


    public Picture() { }

    public Picture(String owner, Date uploadTime,String path) {
        this.owner = owner;
        this.uploadTime = uploadTime;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
