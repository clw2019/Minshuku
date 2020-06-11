package com.clw.config;

import com.clw.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/16 10:57
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Value("${upload.upload-folder}")
    private String uploadFolder;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/carousel/**", "/district/**", "/home/**");
                //.excludePathPatterns("/**")
                //.addPathPatterns("/login")
                //.addPathPatterns("/district");
    }

    /**
     * 对前端无法访问本地资源放行
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + uploadFolder);
    }
}
