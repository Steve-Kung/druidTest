package com.steve.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/21
 * @Description: com.steve.springboot.model
 * @version: 1.0
 */
@Entity // 每个持久化POJO类都是一个实体Bean
@Table(name="ay_user") // 声明数据库的数据表
public class AyUser implements Serializable {
    // 主键
    @Id // 指定主键
    private String id;
    // 用户名
    private String name;
    // 密码
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AyUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
