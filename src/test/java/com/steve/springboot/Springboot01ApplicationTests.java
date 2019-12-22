package com.steve.springboot;

import com.steve.springboot.model.AyUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class Springboot01ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource // 通过注解，实例化一个对象，省去初始化工作
    private JdbcTemplate jdbcTemplate; // JDBC工具类，可以对数据库进行增删改查等操作

    @Test
    public void mysqlTest(){
        String sql = "select id, name, password from ay_user";
        // 实现对数据库查询
        List<AyUser> userList = jdbcTemplate.query(sql, new RowMapper<AyUser>() {
            // RowMapper对象可以将查询出的每一行数据封装成用户自定义的类
            @Override
            public AyUser mapRow(ResultSet resultSet, int i) throws SQLException {
                AyUser ayUser = new AyUser();
                ayUser.setId(resultSet.getString("id"));
                ayUser.setName(resultSet.getString("name"));
                ayUser.setPassword(resultSet.getString("password"));
                return ayUser;
            }
        });
        System.out.println("查询成功：");
        for (AyUser ayUser: userList){
            System.out.println(ayUser.toString());
        }
    }


}
