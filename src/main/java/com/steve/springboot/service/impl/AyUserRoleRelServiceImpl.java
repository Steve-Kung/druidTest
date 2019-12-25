package com.steve.springboot.service.impl;

import com.steve.springboot.model.AyUserRoleRel;
import com.steve.springboot.repository.AyUserRoleRelRepository;
import com.steve.springboot.service.AyUserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.service.impl
 * @version: 1.0
 */
@Service
public class AyUserRoleRelServiceImpl implements AyUserRoleRelService {
    @Resource
    AyUserRoleRelRepository ayUserRoleRelRepository;
    @Override
    public List<AyUserRoleRel> findByUserId(String userId) {
        return ayUserRoleRelRepository.findByUserId(userId);
    }
}
