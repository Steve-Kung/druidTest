package com.steve.springboot.mail.impl;

import com.steve.springboot.mail.SendJunkMailService;
import com.steve.springboot.model.AyUser;
import com.steve.springboot.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/23
 * @Description: com.steve.springboot.mail
 * @version: 1.0
 */
@Service
public class SendJunkMailServiceImpl implements SendJunkMailService {
    @Autowired
    JavaMailSender mailSender; // 邮件发送接口，Springboot已经自动化配置好，直接@Autowired注入进来使用即可
    @Resource
    private AyUserService ayUserService;
    // Value 注解可以将application.properties配置文件中的配置设置到属性中。将spring.mail.username=ay_test@163.com设置给from属性
    @Value("${spring.mail.username}")
    private String from;
    public static final Logger logger =
            LogManager.getLogger(SendJunkMailServiceImpl.class);

    @Override
    public boolean sendJunkMail(List<AyUser> ayUserList) {
        try{
            if(ayUserList == null || ayUserList.size() <= 0 ) return Boolean.FALSE;
            for(AyUser ayUser: ayUserList){
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                //邮件发送方
                message.setFrom(from);
                //邮件主题
                message.setSubject("地瓜今日特卖");
                //邮件接收方
                message.setTo("stevekung98@163.com");
                //邮件内容
                message.setText(ayUser.getName() +" ,你知道么？厦门地瓜今日特卖，一斤只要9元");
                //发送邮件
                this.mailSender.send(mimeMessage);
            }
        }catch(Exception ex){
            logger.error("sendJunkMail error and ayUser=%s", ayUserList, ex);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
