package com.steve.springboot.repository;

import com.steve.springboot.model.AyUserRoleRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.repository
 * @version: 1.0
 */
public interface AyUserRoleRelRepository extends JpaRepository<AyUserRoleRel,String> {
    List<AyUserRoleRel> findByUserId(@Param("userId") String userId);
}
