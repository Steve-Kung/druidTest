package com.steve.springboot;

import com.steve.springboot.model.AyMood;
import com.steve.springboot.model.AyUser;
import com.steve.springboot.producer.AyMoodProducer;
import com.steve.springboot.service.AyMoodService;
import com.steve.springboot.service.AyUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

    @Test
    public void testTransaction(){
        AyUser ayUser = new AyUser();
        ayUser.setId("4");
        ayUser.setName("阿花");
        ayUser.setPassword("123");
        ayUserService.save(ayUser);
        // 由于方法save保存数据时出现空指针异常，数据会回滚，如果保留了@Transaction注解，数据库查不到此条信息。数据更加安全。
    }

    @Resource // 通过注解注入
    private RedisTemplate redisTemplate; // Spring Data Redis为我们提供的模板类，用来对数据继续操作
    @Resource
    private StringRedisTemplate stringRedisTemplate; // String这个只针对键值是字符串的数据进行操作
    @Test
    public void testRedis(){
        // 增
        redisTemplate.opsForValue().set("name", "ay");
        String name = (String)redisTemplate.opsForValue().get("name");
        System.out.println(name);
        // 删
        redisTemplate.delete("name");
//        改
        redisTemplate.opsForValue().set("name", "al");
//        查
        name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    @Test
    public void testFindById(){
        Long redisUserSize = 0L;
        //查询id = 1 的数据，该数据存在于redis缓存中
        AyUser ayUser = ayUserService.findById("1");
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存中的用户数量为：" + redisUserSize);
        System.out.println("--->>> id: " + ayUser.getId() + " name:" + ayUser.getName());
        //查询id = 2 的数据，该数据存在于redis缓存中
        AyUser ayUser1 = ayUserService.findById("2");
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存中的用户数量为：" + redisUserSize);
        System.out.println("--->>> id: " + ayUser1.getId() + " name:" + ayUser1.getName());
        //先在此处打断点，然后在往数据库里插入第5条数据，再接着运行,查询id = 5 的数据，不存在于redis缓存中，存在于数据库中，所以会把数据库查询的数据更新到缓存中
        AyUser ayUser3 = ayUserService.findById("5");
        System.out.println("--->>> id: " + ayUser3.getId() + " name:" + ayUser3.getName());
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存中的用户数量为：" + redisUserSize);

    }

    Logger logger = LogManager.getLogger(this.getClass());
    @Test
    public void testLog4j(){
        ayUserService.delete("4");
        logger.info("delete success!!!");
    }

    @Test
    public void testMybatis(){
        AyUser ayUser = ayUserService.findByNameAndPassword("阿毅", "123456");
        logger.info(ayUser.getId() + ayUser.getName());
        System.out.println(ayUser.getId() + ayUser.getName());
    }

    @Resource
    private AyMoodService ayMoodService;
    @Test
    public void testAyMood(){
        AyMood ayMood = new AyMood();
        ayMood.setId("1");
        // 用户阿毅id为1
        ayMood.setUserId("1");
        ayMood.setPraiseNum(0);
        // 说说内容
        ayMood.setContent("这是我第一条微信说说！！！！");
        ayMood.setPublishTime(new Date());
        // 往数据库保存一条数据。用户阿毅发表了一条说说
        AyMood mood = ayMoodService.save(ayMood);
    }

    @Resource
    private AyMoodProducer ayMoodProducer;
    @Test
    public void testActiveMQ(){
        Destination destination = new ActiveMQQueue("ay.queue");
        ayMoodProducer.sendMessagr(destination,"hello,mq!!!");
    }


}
