package com.tan.service;

import com.tan.dto.DtoSaveArticle;
import com.tan.dto.DtoUpdateArticle;
import com.tan.entity.EntityArticle;
import com.tan.entity.EntityResult;
import com.tan.entity.PageBean;

public interface ServiceArticle {
    /**
     * 添加文章
     * @param dtoSaveArticle
     */
    void save(DtoSaveArticle dtoSaveArticle);

    /**
     * 文章列表
     * @param currentPage
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    PageBean<EntityArticle> list(Integer currentPage, Integer pageSize, String categoryId, String state);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    EntityResult getDetail(Integer id);

    /**
     * 更新文章
     * @param dtoUpdateArticle
     */
    void update(DtoUpdateArticle dtoUpdateArticle);

    /**
     * 删除文章
     * @param id
     */
    void deleteById(Integer id);
}
