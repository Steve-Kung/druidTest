package com.steve.springboot.repository;

import com.steve.springboot.model.AyUserAttachmentRel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.repository
 * @version: 1.0
 */
// 类似于spring-data下的Jpa
    // 继承MongoRepository提供的增删改查等方法
public interface AyUserAttachmentRelRepository extends MongoRepository<AyUserAttachmentRel, String> {

}
