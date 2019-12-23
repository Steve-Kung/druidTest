package com.steve.springboot.mail;

import com.steve.springboot.model.AyUser;

import java.util.List;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/23
 * @Description: com.steve.springboot.mail
 * @version: 1.0
 */
public interface SendJunkMailService {
    boolean sendJunkMail(List<AyUser> ayUser);
}
