package com.steve.springboot.service.impl;

import com.steve.springboot.model.AyUserAttachmentRel;
import com.steve.springboot.repository.AyUserAttachmentRelRepository;
import com.steve.springboot.service.AyUserAttachmentRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.service.impl
 * @version: 1.0
 */
@Service
public class AyUserAttachmentRelServiceImpl implements AyUserAttachmentRelService {
    @Resource
    private AyUserAttachmentRelRepository ayUserAttachmentRelRepository;
    @Override
    public AyUserAttachmentRel save(AyUserAttachmentRel ayUserAttachmentRel) {
        return ayUserAttachmentRelRepository.save(ayUserAttachmentRel);
    }
}
