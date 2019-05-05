package cn.tohuangshuai.pojo.domain;

import java.util.Date;

/**
 * 班级实体类
 */
public class HSClass {

    private String id;

    private Integer grade;  //年级

    private String name;  //班级名称 专业名

    private Integer number; //班级人数

    private String managerId; //班主任ID

    private Date createTime; //创建时间

    public HSClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
