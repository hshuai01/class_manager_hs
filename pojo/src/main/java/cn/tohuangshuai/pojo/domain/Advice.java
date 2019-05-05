package cn.tohuangshuai.pojo.domain;

import java.util.Date;

/**
 * 通知实体类
 */
public class Advice {

    private String id;

    private String title;

    private String context;

    private String imageUrl;

    private String author;

    private Date createTime;

    private String classId;

    private Integer status;

    //新增字段
    private String authorName;

    private String authorFace;

    private Integer authorStatus;

    public Advice() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorFace() {
        return authorFace;
    }

    public void setAuthorFace(String authorFace) {
        this.authorFace = authorFace;
    }

    public Integer getAuthorStatus() {
        return authorStatus;
    }

    public void setAuthorStatus(Integer authorStatus) {
        this.authorStatus = authorStatus;
    }
}
