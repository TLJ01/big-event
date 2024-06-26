package com.tan.mapper;

import com.tan.dto.DtoUpdateCategory;
import com.tan.entity.EntityCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MapperCategory {
    @Select("Select * from category where category_name=#{categoryName}")
    EntityCategory getCategoryByName(String categoryName);

    @Insert("insert into category (category_name, category_alias, create_user, create_time, update_time) value (#{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void addCategory(EntityCategory category);
    @Select("select * from category where create_user=#{userId}")
    List<EntityCategory> list(Integer userId);

    @Select("select * from category where id=#{id}")
    EntityCategory getDetail(Integer id);

    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=now() where id=#{id}")
    void update(DtoUpdateCategory category);

    @Delete("delete from category where id=#{id}")
    void deleteById(Integer id);
}
