package com.steve.springboot.producer;


import com.steve.springboot.model.AyMood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/24
 * @Description: com.steve.springboot.producer
 * @version: 1.0
 */
@Service
public class AyMoodProducer {
    @Resource //JmsMessagingTemplate发消息的工具类，参数destination是发送到队列的，message是待发送的消息
    private JmsMessagingTemplate jmsMessagingTemplate;
    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
    public void sendMessage(Destination destination, final AyMood ayMood){
        jmsMessagingTemplate.convertAndSend(destination,ayMood);
    }
}
