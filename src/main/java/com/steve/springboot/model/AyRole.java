package com.steve.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.model
 * @version: 1.0
 */
@Entity
@Table(name = "ay_role")
public class AyRole {
    @Id
    private String id;
    private String name;

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
}
