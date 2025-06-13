package com.weilai.system.config;

import com.weilai.system.interceptor.loginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webMVCconfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor())
                //需要拦截的请求
                .addPathPatterns("/*/")
                //放行的请求
                .excludePathPatterns
                        ("/user/login/","/user/register/"
                        ,"/blog/pages","/blog/{id}","/blog/hotblog",
                                "/blog/search","/blog/searchtitle",
                                "/tag/tags","/tag/tagid",
                                "/module/get",
                                "/review/findall"
                                ,"/review/islike"
                                ,"/chat/**");

    }
}
