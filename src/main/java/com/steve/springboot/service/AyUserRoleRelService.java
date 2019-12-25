package com.steve.springboot.service;

import com.steve.springboot.model.AyUserRoleRel;

import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.service
 * @version: 1.0
 */
public interface AyUserRoleRelService {
    List<AyUserRoleRel> findByUserId(String userId);
}
