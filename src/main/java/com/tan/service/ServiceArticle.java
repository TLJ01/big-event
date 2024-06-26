package com.tan.service;

import com.tan.dto.DtoSaveArticle;

public interface ServiceArticle {
    /**
     * 添加文章
     * @param dtoSaveArticle
     */
    void save(DtoSaveArticle dtoSaveArticle);
}
