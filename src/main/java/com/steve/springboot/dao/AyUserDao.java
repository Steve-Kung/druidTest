package com.steve.springboot.dao;

import com.steve.springboot.model.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/24
 * @Description: com.steve.springboot.dao
 * @version: 1.0
 */
@Mapper // mybatis根据接口定义与Mapper文件中的SQL语句动态创建接口实现
public interface AyUserDao {
    // 通过用户名和密码查询用户
    // @Param注解参数，在Mapper.xml配置文件中，可以采用#{}的方式对@Param注解括号内的参数进行引用
    AyUser findByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
