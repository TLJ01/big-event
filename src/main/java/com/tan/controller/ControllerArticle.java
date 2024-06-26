package com.tan.controller;

import com.tan.dto.DtoSaveArticle;
import com.tan.entity.EntityResult;
import com.tan.service.ServiceArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ControllerArticle {

    @Autowired
    private ServiceArticle serviceArticle;

    /**
     * 添加文章
     * @param dtoSaveArticle
     * @return
     */
    @PostMapping
    public EntityResult saveArticle(@RequestBody DtoSaveArticle dtoSaveArticle) {
        serviceArticle.save(dtoSaveArticle);
        return EntityResult.success();
    }

}
