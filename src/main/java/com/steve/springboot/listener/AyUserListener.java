package com.steve.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext 上下文初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext 上下文销毁");
    }
}
