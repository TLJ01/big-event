package com.tan.service;


import com.tan.entity.EntityResult;

public interface ServiceUser {
    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    EntityResult register(String username, String password);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    EntityResult login(String username, String password);


    /**
     * 获取用户信息
     * @return
     */
    EntityResult getUserInfo();
}
