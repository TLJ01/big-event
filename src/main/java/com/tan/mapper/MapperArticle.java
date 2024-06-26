package com.tan.mapper;

import com.tan.dto.DtoSaveArticle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapperArticle {
    @Insert("insert into article (title, content, cover_img, category_id,state, create_user, create_time, update_time) " +
            "value (#{title},#{content},#{coverImg},#{categoryId},#{state},#{createUser},#{createTime},#{updateTime})")
    void save(DtoSaveArticle dtoSaveArticle);
}
