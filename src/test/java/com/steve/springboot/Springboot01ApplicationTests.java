package com.steve.springboot;

import com.steve.springboot.model.AyUser;
import com.steve.springboot.service.AyUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Resource
    private AyUserService ayUserService;
    @Test
    public void testRepository(){
        // 查询所有数据
        List<AyUser> userList = ayUserService.findAll();
        System.out.println("findAll(): "+ userList.size());
        // 通过name查询数据
        List<AyUser> userList2 = ayUserService.findByName("阿毅");
        System.out.println("findByName(): "+ userList2.size());
        Assert.isTrue(userList2.get(0).getName().equals("阿毅"),"data error!");
        // Assert断言是软件开发中一种常见的调试方式
        // 通过name模糊查询数据
        List<AyUser> userList3 = ayUserService.findByNameLike("%毅%");
        System.out.println("findByNameLike(): " + userList3.size());
        Assert.isTrue(userList3.get(0).getName().equals("阿毅"),"data error!");
        // 通过id列表查询数据
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        List<AyUser> userList4 = ayUserService.findByIdIn(ids);
        System.out.println("findByIdIn(): "+userList4.size());
        // 分页查询数据
        // 把原来的 new PageRequest 改成 PageRequest.of(0,10);
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<AyUser> userList5 = ayUserService.findAll(pageRequest);
        System.out.println("findAll(): " + userList5.getTotalPages()+"/"+userList5.getSize());
        // 新增数据
        AyUser ayUser = new AyUser();
        ayUser.setId("4");
        ayUser.setName("test");
        ayUser.setPassword("1230");
        ayUserService.save(ayUser);
        // 删除数据
        ayUserService.delete("4");


    }
}
