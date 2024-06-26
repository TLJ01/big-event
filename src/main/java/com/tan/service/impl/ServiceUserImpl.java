package com.tan.service.impl;

import com.tan.entity.EntityResult;
import com.tan.entity.EntityUser;
import com.tan.mapper.MapperUser;
import com.tan.service.ServiceUser;
import com.tan.utils.JwtUtil;
import com.tan.utils.Md5Util;
import com.tan.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceUserImpl implements ServiceUser {

    @Autowired
    private MapperUser mapperUser;


    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public EntityResult register(String username, String password) {
        //判断当前用户是否存在
        EntityUser user = mapperUser.getUserByName(username);
        if (user != null) {
            return EntityResult.error("当前用户已存在");
        }
        //注册
        //加密
        String md5Password = Md5Util.getMD5String(password);
        mapperUser.register(username,md5Password);
        return EntityResult.success();
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public EntityResult login(String username, String password) {
        //判断当前用户名是否存在
        EntityUser user = mapperUser.getUserByName(username);
        if (user == null) {
            return EntityResult.error("用户名不存在");
        }else{
            //获取密码
            String md5Password = Md5Util.getMD5String(password);
            if (user.getPassword().equals(md5Password)) {
                //生成token
                Map<String, Object> claims = new HashMap<>();
                claims.put("username", username);
                claims.put("id", user.getId());
                String token = JwtUtil.genToken(claims);

                //返回token
                return EntityResult.success(token);
            }else{
                return EntityResult.error("密码错误");
            }
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public EntityResult getUserInfo() {

        Map<String, Object> map = ThreadLocalUtil.get();

        String username = (String) map.put("username", map.get("username"));

        //查找用户
        EntityUser user = mapperUser.getUserByName(username);

        return EntityResult.success(user);
    }
}
