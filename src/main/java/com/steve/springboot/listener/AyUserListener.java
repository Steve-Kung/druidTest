package com.steve.springboot.listener;

import com.steve.springboot.model.AyUser;
import com.steve.springboot.service.AyUserService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/22
 * @Description: com.steve.springboot.listener
 * @version: 1.0
 */
// WebListener将一个类声明为监听器，被spring容器管理，利用相应属性进行配置，与web.xml文件中配置监听器作用一致
    // 可以用作统计在线人数或者结合缓存技术缓存用户数据，提高系统性能
// ServletContextListener监听整个Web应用的生命周期，当Servlet容器启动或者终止web应用时，会触发ServletContextEvent事件
    // 启动应用时，触发contextInitialized方法，之后容器再对Filter初始化，Servlet初始化
    // 终止应用时，触发contextDestroyed方法之前，容器先对Filter、Servlet销毁
// HttpSessionListener监听整个会话的生命周期，当Session创建和关闭时，会触发相应事件
// ServletRequestListener监听整个request的生命周期，当客户端发送请求request创建和关闭时，会触发相应事件
@WebListener
public class AyUserListener implements ServletContextListener {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private AyUserService ayUserService;
    private static final String ALL_USER = "ALL_USER_LIST";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext 上下文初始化");
        // 加载数据库中所有的用户数据，并存放在Redis缓存中，提高数据访问速度，避免频繁连接数据库
        // 查询数据库所有用户
        List<AyUser> ayUserList = ayUserService.findAll();
        // 清除缓存中的用户数据
        redisTemplate.delete(ALL_USER);
        // 将数据存放到Redis缓存中
        redisTemplate.opsForList().leftPushAll(ALL_USER,ayUserList);
        // 在真实项目中需要注释掉，查询所有的用户数据
        List<AyUser> queryUserList = redisTemplate.opsForList().range(ALL_USER, 0,-1);
        System.out.println("缓存中目前的用户数有：" + queryUserList.size() + "人");
        System.out.println("用于统计人数");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext 上下文销毁");
    }
}
