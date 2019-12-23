package com.steve.springboot.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/23
 * @Description: com.steve.springboot.quartz
 * @version: 1.0
 */
public class TestTask {
    // 日志对象
    private static final Logger logger = LogManager.getLogger(TestTask.class);
    public void run(){
        logger.info("定时器运行了!!!");
    }
}
