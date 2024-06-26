package com.tan.service.impl;

import com.tan.dto.DtoUpdateCategory;
import com.tan.entity.EntityCategory;
import com.tan.entity.EntityResult;
import com.tan.mapper.MapperCategory;
import com.tan.service.ServiceCategory;
import com.tan.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    /**
     * 获取分类列表
     * @return
     */
    @Override
    public EntityResult<List<EntityCategory>> list() {

        //注意,这里应该是获取当前用户的分类列表
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        List<EntityCategory> list =  mapperCategory.list(userId);
        return EntityResult.success(list);
    }

    /**
     * 获取分类详情
     * @param id
     * @return
     */
    @Override
    public EntityResult getDetail(Integer id) {
        EntityCategory entityCategory = mapperCategory.getDetail(id);
        return EntityResult.success(entityCategory);
    }

    /**
     * 更新分类
     * @param category
     */
    @Override
    public void update(DtoUpdateCategory category) {
        mapperCategory.update(category);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        mapperCategory.deleteById(id);
    }
}
