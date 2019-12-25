package com.steve.springboot.service.impl;

import com.steve.springboot.error.BusinessException;
import com.steve.springboot.model.AyUser;
import com.steve.springboot.model.AyUserRoleRel;
import com.steve.springboot.service.AyRoleService;
import com.steve.springboot.service.AyUserRoleRelService;
import com.steve.springboot.service.AyUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.service.impl
 * @version: 1.0
 */
//@Service
public class CustomUserService implements UserDetailsService {
    @Resource
    private AyUserService ayUserService;
    @Resource
    private AyUserRoleRelService ayUserRoleRelService;
    @Resource
    private AyRoleService ayRoleService;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        AyUser ayUser = ayUserService.findByUserName(name);
        if (ayUser == null){
            throw new BusinessException("用户不存在");
        }
        // 获取用户所有的关联角色
        List<AyUserRoleRel> ayRoleList = ayUserRoleRelService.findByUserId(ayUser.getId());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if(ayRoleList != null && ayRoleList.size()>0){
            for (AyUserRoleRel rel : ayRoleList){
                // 获取用户关联角色名称
                String roleName = ayRoleService.find(rel.getRoleId()).getName();
                authorityList.add(new SimpleGrantedAuthority(roleName));
            }
        }
        return new User(ayUser.getName(), ayUser.getPassword(), authorityList);
    }
}
