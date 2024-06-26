package com.tan.mapper;

import com.tan.dto.DtoSaveArticle;
import com.tan.dto.DtoUpdateArticle;
import com.tan.entity.EntityArticle;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MapperArticle {
    @Insert("insert into article (title, content, cover_img, category_id,state, create_user, create_time, update_time) " +
            "value (#{title},#{content},#{coverImg},#{categoryId},#{state},#{createUser},#{createTime},#{updateTime})")
    void save(DtoSaveArticle dtoSaveArticle);

    List<EntityArticle> list(Integer userId, String categoryId, String state);

    @Select("select * from article where id=#{id}")
    EntityArticle getDetail(Integer id);

    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId}," +
            "update_time=now() where id=#{id}")
    void updte(DtoUpdateArticle dtoUpdateArticle);

    @Delete("delete from article where id=#{id}")
    void deleteById(Integer id);
}
