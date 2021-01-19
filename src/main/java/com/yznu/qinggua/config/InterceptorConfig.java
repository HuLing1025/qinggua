package com.yznu.qinggua.config;

import com.yznu.qinggua.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/desktop/**")
                .addPathPatterns("/cms/**")
                // 不拦截客户端登录注册接口
                .excludePathPatterns("/desktop/user/login")
                .excludePathPatterns("/desktop/user/logon")
                // 不拦截客户端放映计划接口
                .excludePathPatterns("/desktop/schedule/**")
                // 不拦截客户端搜索电影接口
                .excludePathPatterns("/desktop/film/**")
                // 不拦截后台登录注册接口
                .excludePathPatterns("/cms/admin/login")
                .excludePathPatterns("/cms/admin/logon")
                // 不拦截test/开头的测试接口
                .excludePathPatterns("/test/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .addResourceLocations("/resources/**");
    }
}