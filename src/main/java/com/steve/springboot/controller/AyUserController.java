package com.steve.springboot.controller;

import com.steve.springboot.error.BusinessException;
import com.steve.springboot.model.AyUser;
import com.steve.springboot.service.AyUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/22
 * @Description: com.steve.springboot.controller
 * @version: 1.0
 */
@Controller //标注此类是一个控制层类，同时让Spring Boot容器管理起来
@RequestMapping("/ayUser") // 处理请求地址映射的注解
public class AyUserController {
    @Resource
    private AyUserService ayUserService;
    @RequestMapping("/test")
    public String test(Model model){
        // 查询数据库所有用户
        // Model接口，可以吧数据库查询出来的数据设置到该类中，前端会从该对象获取数据
        List<AyUser> ayUser = ayUserService.findAll();
        model.addAttribute("users", ayUser);
        return "ayUser";
    }
    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<AyUser> ayUser = ayUserService.findAll();
        model.addAttribute("users",ayUser);
        throw new BusinessException("业务异常");
    }
    @RequestMapping("/findByNameAndPasswordRetry")
    public String findByNameAndPasswordRetry(Model model){
        AyUser ayUser = ayUserService.findByNameAndPasswordRetry("阿毅", "123456");
        model.addAttribute("users", ayUser);
        return "success";
    }
}
