package com.tan.service;

import com.tan.dto.DtoUpdateCategory;
import com.tan.entity.EntityCategory;
import com.tan.entity.EntityResult;

import java.util.List;

public interface ServiceCategory {
    /**
     * 新增文章分类
     * @param category
     * @return
     */
    EntityResult addCategory(EntityCategory category);

    /**
     * 获取分类列表
     * @return
     */
    EntityResult<List<EntityCategory>> list();

    /**
     * 获取分类详情
     * @param id
     * @return
     */
    EntityResult getDetail(Integer id);

    /**
     * 更新分类
     * @param category
     */
    void update(DtoUpdateCategory category);
}
