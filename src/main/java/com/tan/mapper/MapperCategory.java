package com.tan.mapper;

import com.tan.entity.EntityCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MapperCategory {
    @Select("Select * from category where category_name=#{categoryName}")
    EntityCategory getCategoryByName(String categoryName);

    @Insert("insert into category (category_name, category_alias, create_user, create_time, update_time) value (#{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void addCategory(EntityCategory category);
}
