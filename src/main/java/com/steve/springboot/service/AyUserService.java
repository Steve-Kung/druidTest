package com.steve.springboot.service;

import com.steve.springboot.model.AyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/22
 * @Description: com.steve.springboot.service
 * @version: 1.0
 */
public interface AyUserService {
    // 增删改查
    AyUser findById(String id);
    List<AyUser> findAll();
    AyUser save(AyUser ayUser);// 同时具备保存和更新数据的功能
    void delete(String id);

    // 分页
    // Pageable 分页接口，查询时只需要传入一个其实现类，指定pageNumber为第几页，pageSize为每一页的大小
    // Page 分页查询结果会封装在该类中
    Page<AyUser> findAll(Pageable pageable);

    // 自定义查询方法
    List<AyUser> findByName(String name);
    List<AyUser> findByNameLike(String name);
    List<AyUser> findByIdIn(Collection<String> ids);
}
