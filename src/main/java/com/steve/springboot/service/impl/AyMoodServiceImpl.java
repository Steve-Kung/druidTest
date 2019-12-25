package com.steve.springboot.service.impl;

import com.steve.springboot.model.AyMood;
import com.steve.springboot.producer.AyMoodProducer;
import com.steve.springboot.repository.AyMoodRepository;
import com.steve.springboot.service.AyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;


/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/24
 * @Description: com.steve.springboot.service.impl 微信说说服务层
 * @version: 1.0
 */
@Service
public class AyMoodServiceImpl implements AyMoodService {
    @Resource // 注入AyMoodRepository接口
    private AyMoodRepository ayMoodRepository;

    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepository.save(ayMood);
    }

    // 队列
    private static Destination destination = new ActiveMQQueue("ay.queue.asyn.save");
    @Resource
    private AyMoodProducer ayMoodProducer;

    @Override
    public String asynSave(AyMood ayMood) {
        // 往队列ay.queue.asyn.save推送消息，消息内容为说说实体
        ayMoodProducer.sendMessage(destination,ayMood);
        return "success";
    }
}
