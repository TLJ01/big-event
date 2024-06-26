package com.tan.controller;

import com.tan.entity.EntityCategory;
import com.tan.entity.EntityResult;
import com.tan.service.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ControllerCategory {

    @Autowired
    private ServiceCategory serviceCategory;

    /**
     * 新增文章分类
     * @param category
     * @return
     */
    @PostMapping("/category")
    public EntityResult addCategory(@RequestBody @Validated EntityCategory category) {
        return serviceCategory.addCategory(category);
    }

}
