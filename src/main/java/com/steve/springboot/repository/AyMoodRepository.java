package com.steve.springboot.repository;

import com.steve.springboot.model.AyMood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/24
 * @Description: com.steve.springboot.repository
 * @version: 1.0
 */
public interface AyMoodRepository extends JpaRepository<AyMood, String> {
}
