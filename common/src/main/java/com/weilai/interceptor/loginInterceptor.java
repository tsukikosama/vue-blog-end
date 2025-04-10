package com.weilai.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class loginInterceptor implements HandlerInterceptor {
    /**
     * 前置拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求头中是否有token
//        String token = UserHolder.getUser();
//        System.out.println(token);
        if(request.getHeader("authority") == null){
            response.setStatus(401);
            return false;
        }
        return true;
    }


}
