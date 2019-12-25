package com.steve.springboot.service.impl;

import com.steve.springboot.dao.AyUserDao;
import com.steve.springboot.error.BusinessException;
import com.steve.springboot.model.AyUser;
import com.steve.springboot.repository.AyUserRepository;
import com.steve.springboot.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/22
 * @Description: com.steve.springboot.service.impl
 * @version: 1.0
 */
@Transactional // 一般在服务层通过注解来开启事务，注解在类上，表明此类所有public方法都是开启事务的
@Service // 表明是service服务层类，并纳入Spring容器管理
public class AyUserServiceImpl implements AyUserService {
    @Resource(name = "ayUserRepository") // 默认按照名称进行装配，也可通过name属性指定
    private AyUserRepository ayUserRepository;

    @Resource
    private RedisTemplate redisTemplate;
    private static final String ALL_USER = "ALL_USER_LIST";

    Logger logger = LogManager.getLogger(this.getClass());


    // 实现增删改查
    @Override
    public AyUser findById(String id) {
        // 1、查询Redis缓存中的所有数据
        List<AyUser> ayUserList = redisTemplate.opsForList().range(ALL_USER,0,-1);
        if(ayUserList != null && ayUserList.size() > 0){
            for(AyUser ayUser : ayUserList){
                if(ayUser.getId().equals(id)){
                    return ayUser;
                }
            }
        }
        // 2、查询数据库中的数据
        AyUser ayUser = ayUserRepository.findById(id).get();
        if(ayUser != null){
            // 3、将数据插入Redis缓存中
            redisTemplate.opsForList().leftPush(ALL_USER,ayUser);
        }
        return ayUser;
    }

    @Override
    public List<AyUser> findAll() {
        try {
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务耗时：" + (end-start)+"ms");
            return ayUserList;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("method [findAll] error", e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    @Async
    public Future<List<AyUser>> findAsynAll() {
        try {
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务耗时：" + (end-start)+"ms");
            return new AsyncResult<List<AyUser>>(ayUserList);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("method [findAll] error", e);
            return new AsyncResult<List<AyUser>>(null);
        }
    }

    @Transactional
    @Override
    public AyUser save(AyUser ayUser) {
        AyUser saveUser = ayUserRepository.save(ayUser);
        // 出现空指针异常
//        String error = null;
//        error.split("/");
        return saveUser;
    }

    @Override
    public void delete(String id) {
        ayUserRepository.deleteById(id);
        logger.info("userId: " + id + "用户被删除");
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

    @Resource
    private AyUserDao ayUserDao;
    @Override
    public AyUser findByNameAndPassword(String name, String password) {
        return ayUserDao.findByNameAndPassword(name, password);
    }

    @Override
    // value属性表示出现哪些异常的时候触发重试，maxAttempts表示最大重试次数默认为3，
    // delay表示重试的延迟时间，multiplier表示上一次延时是这一次的倍数
    @Retryable(value = {BusinessException.class},maxAttempts = 5,
            backoff = @Backoff(delay = 5000, multiplier = 2))
    public AyUser findByNameAndPasswordRetry(String name, String password) {
        System.out.println("[findByNameAndPasswordRetry] 方法失败重试了！");
        throw new BusinessException();
    }

    @Override
    public AyUser findByUserName(String name) {
        List<AyUser> ayUsers = findByName(name);
        if(ayUsers == null && ayUsers.size() <= 0){
            return null;
        }
        return ayUsers.get(0);
    }
}
