package com.tan.service;

import com.tan.entity.EntityCategory;
import com.tan.entity.EntityResult;

public interface ServiceCategory {
    /**
     * 新增文章分类
     * @param category
     * @return
     */
    EntityResult addCategory(EntityCategory category);
}
