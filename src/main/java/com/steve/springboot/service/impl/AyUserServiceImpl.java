package com.steve.springboot.service.impl;

import com.steve.springboot.model.AyUser;
import com.steve.springboot.repository.AyUserRepository;
import com.steve.springboot.service.AyUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/22
 * @Description: com.steve.springboot.service.impl
 * @version: 1.0
 */
@Service // 表明是service服务层类，并纳入Spring容器管理
public class AyUserServiceImpl implements AyUserService {
    @Resource(name = "ayUserRepository") // 默认按照名称进行装配，也可通过name属性指定
    private AyUserRepository ayUserRepository;

    // 实现增删改查
    @Override
    public AyUser findById(String id) {
        return ayUserRepository.findById(id).get();
    }

    @Override
    public List<AyUser> findAll() {
        return ayUserRepository.findAll();
    }

    @Override
    public AyUser save(AyUser ayUser) {
        return ayUserRepository.save(ayUser);
    }

    @Override
    public void delete(String id) {
        ayUserRepository.deleteById(id);
    }

    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll(pageable);
    }

    @Override
    public List<AyUser> findByName(String name) {
        return ayUserRepository.findByName(name);
    }

    @Override
    public List<AyUser> findByNameLike(String name) {
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(Collection<String> ids) {
        return ayUserRepository.findByIdIn(ids);
    }
}
