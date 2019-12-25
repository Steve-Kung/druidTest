package com.steve.springboot.security;

import com.steve.springboot.service.impl.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/25
 * @Description: com.steve.springboot.security
 * @version: 1.0
 */
@Configuration
@EnableWebSecurity //开启Security安全框架
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public CustomUserService customUserService(){
        return new CustomUserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 路由策略和访问权限的简单配置
        http.formLogin() // 启用默认登录页面
            .failureUrl("/login?error") // 登录失败返回URL:/login?error
            .defaultSuccessUrl("/ayUser/test") // 登录成功跳转URL,这里跳转到用户首页
            .permitAll(); //登录页面全部权限可访问
        super.configure(http);
    }
    // 配置内存用户
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
//                .userDetailsService(customUserService);
                .userDetailsService(customUserService());
//                .inMemoryAuthentication() // 可添加内存中的用户，并给用户指定角色权限
//                .withUser("阿毅").password("123456").roles("ADMIN")
//                .and()
//                .withUser("阿兰").password("123456").roles("USER");
    }
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
