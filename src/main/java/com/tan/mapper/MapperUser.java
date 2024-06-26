package com.tan.mapper;

import com.tan.entity.EntityUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.GetMapping;

@Mapper
public interface MapperUser {
    @Select("select * from user where username = #{username}")
    EntityUser getUserByName(String username);

    @Insert("insert into user (username,password,create_time,update_time) value (#{username},#{md5Password},now(),now())")
    void register(String username, String md5Password);

    @Update("update user set username=#{username},nickname=#{nickname},email = #{email}")
    void update(EntityUser user);
}
