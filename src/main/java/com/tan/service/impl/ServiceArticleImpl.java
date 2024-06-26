package com.tan.service.impl;

import com.tan.dto.DtoSaveArticle;
import com.tan.mapper.MapperArticle;
import com.tan.service.ServiceArticle;
import com.tan.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ServiceArticleImpl implements ServiceArticle {

    @Autowired
    private MapperArticle mapperArticle;

    /**
     * 添加文章
     * @param dtoSaveArticle
     */
    @Override
    public void save(DtoSaveArticle dtoSaveArticle) {

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        dtoSaveArticle.setCreateUser(userId);
        dtoSaveArticle.setCreateTime(LocalDateTime.now());
        dtoSaveArticle.setUpdateTime(LocalDateTime.now());

        mapperArticle.save(dtoSaveArticle);

    }
}
