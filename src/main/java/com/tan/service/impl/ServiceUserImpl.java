package com.tan.service.impl;

import com.tan.entity.EntityResult;
import com.tan.entity.EntityUser;
import com.tan.mapper.MapperUser;
import com.tan.service.ServiceUser;
import com.tan.utils.JwtUtil;
import com.tan.utils.Md5Util;
import com.tan.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class ServiceUserImpl implements ServiceUser {

    @Autowired
    private MapperUser mapperUser;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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


                //存入redis
                stringRedisTemplate.opsForValue().set(username+":token",token);
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
        log.info("用户信息:{}",user);
        return EntityResult.success(user);
    }

    /**
     * 更新用户基本信息
     * @param user
     */
    @Override
    public void update(EntityUser user) {
        //设置更新时间
        user.setUpdateTime(LocalDateTime.now());

        mapperUser.update(user);
    }

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        mapperUser.updateAvatar(avatarUrl,id);
    }

    /**
     * 更新密码
     * @param claims
     * @return
     */
    @Override
    public EntityResult updatePwd(Map<String, String> claims) {
        //获取数据
        String rawPassword = claims.get("rawPassword");
        String newPassword = claims.get("newPassword");
        String rePassword = claims.get("rePassword");

        if (!StringUtils.hasLength(rawPassword)||!StringUtils.hasLength(newPassword)||!StringUtils.hasLength(rePassword)) {
            return EntityResult.error("参数不合法");
        }

        //获取原密码
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        EntityUser loginUser = mapperUser.getUserByName(username);

        //验证原密码
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(rawPassword))) {
            return EntityResult.error("原密码错误");
        }

        //两次密码比对
        if (!newPassword.equals(rePassword)) {
            return EntityResult.error("两次密码不一致");
        }

        //注册
        Integer userId = (Integer) map.get("id");
        String md5String = Md5Util.getMD5String(newPassword);
        mapperUser.updatePwd(md5String,userId);

        //删除redis的token
        Boolean delete = stringRedisTemplate.opsForValue().getOperations().delete(username + ":token");
        log.info("rs:{}",delete);
        return EntityResult.success();

    }
}
