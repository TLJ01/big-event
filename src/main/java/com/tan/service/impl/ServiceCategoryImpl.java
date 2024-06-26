package com.tan.service.impl;

import com.tan.entity.EntityCategory;
import com.tan.entity.EntityResult;
import com.tan.mapper.MapperCategory;
import com.tan.service.ServiceCategory;
import com.tan.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class ServiceCategoryImpl implements ServiceCategory {

    private static final Logger log = LoggerFactory.getLogger(ServiceCategoryImpl.class);
    @Autowired
    private MapperCategory mapperCategory;

    /**
     * 新增文章分类
     * @param category
     * @return
     */
    @Override
    public EntityResult addCategory(EntityCategory category) {
        //判断该分类是否存在
        String categoryName = category.getCategoryName();

        //根据分类名查分类
        EntityCategory entityCategory = mapperCategory.getCategoryByName(categoryName);

        if (!Objects.isNull(entityCategory)){
            //当前分类名已经存在
            return EntityResult.error("分类名已存在");
        }

        //添加
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        mapperCategory.addCategory(category);
        return EntityResult.success();

    }
}
