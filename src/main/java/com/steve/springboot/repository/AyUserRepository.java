package com.steve.springboot.repository;

import com.steve.springboot.model.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/22
 * @Description: com.steve.springboot.repository
 * @version: 1.0
 */
public interface AyUserRepository extends JpaRepository<AyUser, String> {
    // 具有Jpa默认的增删改查方法

    // 也可以自定义查询方法（注意函数命名规范关键字 findBy Id In Like 等）
    List<AyUser> findByName(String name);
    List<AyUser> findByNameLike(String name);
    List<AyUser> findByIdIn(Collection<String> ids);
}
