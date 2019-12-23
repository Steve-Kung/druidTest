package com.steve.springboot.quartz;

import com.steve.springboot.mail.SendJunkMailService;
import com.steve.springboot.model.AyUser;
import com.steve.springboot.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/23
 * @Description: com.steve.springboot.quartz
 * @version: 1.0
 */
@Component
@Configurable // 相当于xml配置文件，可以被SpringBoot扫描初始化
@EnableScheduling // 开启对计划任务的支持，然后要在任务方法上注解@Scheduled,声明这是一个计划任务
public class SendMailQuartz {
    // 日志对象
    private static final Logger logger = LogManager.getLogger(SendMailQuartz.class);
//    // 每5s执行一次
//    @Scheduled(cron = "*/5 * *  * * * ") // 注解为定时任务，在cron表达式里写执行的时机
//    public void reportCurrentByCron(){
//        logger.info("定时器运行了！！！！");
//    }

    @Resource
    private SendJunkMailService sendJunkMailService;
    @Resource
    private AyUserService ayUserService;


    //每5秒执行一次
    @Scheduled(cron = "*/5 * *  * * * ")
    public void reportCurrentByCron(){
        List<AyUser> userList = ayUserService.findAll();
        if (userList == null || userList.size() <= 0) return;
        //发送邮件
        sendJunkMailService.sendJunkMail(userList);
        logger.info("定时器运行了!!!");

    }
}
