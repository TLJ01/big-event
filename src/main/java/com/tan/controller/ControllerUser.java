package com.tan.controller;

import com.tan.entity.EntityResult;
import com.tan.entity.EntityUser;
import com.tan.mapper.MapperUser;
import com.tan.service.ServiceUser;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@Validated
public class ControllerUser {

    @Autowired
    private ServiceUser serviceUser;
    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public EntityResult register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password) {
        return serviceUser.register(username,password);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public EntityResult login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        return serviceUser.login(username,password);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/userInfo")
    public EntityResult getUserInfo(){
        return serviceUser.getUserInfo();
    }

    /**
     * 更新用户基本信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public EntityResult update(@RequestBody @Validated EntityUser user){
        serviceUser.update(user);
        return EntityResult.success();
    }

    /**
     * 更新头像
     * @param avatarUrl
     * @return
     */
    @PatchMapping("/updateAvatar")
    public EntityResult updateAvatar(@RequestParam @URL String avatarUrl){
        serviceUser.updateAvatar(avatarUrl);
        return EntityResult.success();
    }

    /**
     * 更新密码
     * @return
     */
    @PatchMapping("/updatePwd")
    public EntityResult updatePwd(@RequestBody Map<String,String> claims){
        return serviceUser.updatePwd(claims);
    }

}
