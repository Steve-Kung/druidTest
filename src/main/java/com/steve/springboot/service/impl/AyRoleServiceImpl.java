package com.steve.springboot.service.impl;

import com.steve.springboot.model.AyRole;
import com.steve.springboot.repository.AyRoleRepository;
import com.steve.springboot.service.AyRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.service.impl
 * @version: 1.0
 */
@Service
public class AyRoleServiceImpl implements AyRoleService {
    @Resource
    private AyRoleRepository ayRoleRepository;
    @Override
    public AyRole find(String id) {
        return ayRoleRepository.findById(id).get();
    }
}
