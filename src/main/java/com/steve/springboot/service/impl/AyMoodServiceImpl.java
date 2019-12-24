package com.steve.springboot.service.impl;

import com.steve.springboot.model.AyMood;
import com.steve.springboot.repository.AyMoodRepository;
import com.steve.springboot.service.AyMoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/24
 * @Description: com.steve.springboot.service.impl
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
}
