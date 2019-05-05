package cn.tohuangshuai.pojo.domain;


import javax.persistence.Id;

public class User {

    @Id
    private Integer id;


    private String name;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
