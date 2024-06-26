package com.tan.interceptors;

import com.tan.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取token
        String token = request.getHeader("Authorization");
        //解析token
        try {
            Map<String, Object> map = JwtUtil.parseToken(token);
            //解析成功--放行
            return true;
        } catch (Exception e) {
            //解析失败,设置状态码
            response.setStatus(401);
            return false;
        }
    }
}
