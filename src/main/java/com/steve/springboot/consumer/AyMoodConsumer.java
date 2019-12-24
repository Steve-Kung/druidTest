package com.steve.springboot.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/24
 * @Description: com.steve.springboot.consumer
 * @version: 1.0
 */
@Component
public class AyMoodConsumer {
    // 使用JmsListener配置消费者监听的队列ay.queue,其中text是接收到的消息
    @JmsListener(destination = "ay.queue")
    public void receiveQueue(String text){
        System.out.println("用户发表说说【" + text + "】成功了");
    }
}
