package com.tan.interceptors;

import com.tan.utils.JwtUtil;
import com.tan.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取token
        String token = request.getHeader("Authorization");
        //解析token
        try {
            Map<String, Object> map = JwtUtil.parseToken(token);

            String redisToken = stringRedisTemplate.opsForValue().get(map.get("username") + ":token");

            //token失效
            if (redisToken == null || !redisToken.equals(token)) {
                throw new RuntimeException();
            }

            //存入ThreadLocal
            ThreadLocalUtil.set(map);
            //解析成功--放行
            return true;
        } catch (Exception e) {
            //解析失败,设置状态码
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //因为线程周期很长,所以在请求结束之后,清除掉线程里面的内容,防止内存泄露
        ThreadLocalUtil.remove();
    }
}
