package com.steve.springboot.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Auther: http://www.stevekung.com
 * @Date: 2019/12/22
 * @Description: com.steve.springboot.filter
 * @version: 1.0
 */

// WebFilter将一个类声明为过滤器，被spring容器管理，利用相应属性进行配置，与web.xml文件中配置过滤器作用一致
    // filterName用于指定过滤器的名字， urlPatterns用于指定一组过滤器的URL匹配模式
    // value属性等价于urlPatterns属性，但两者不可以同时使用
@WebFilter(filterName = "ayUserFilter", urlPatterns = "/*")
public class AyUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------->>> init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------->>> doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("------->>> destory");
    }
}
