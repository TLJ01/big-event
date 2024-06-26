package com.tan.controller;

import com.tan.dto.DtoUpdateCategory;
import com.tan.entity.EntityCategory;
import com.tan.entity.EntityResult;
import com.tan.service.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
public class ControllerCategory {

    @Autowired
    private ServiceCategory serviceCategory;

    /**
     * 新增文章分类
     * @param category
     * @return
     */
    @PostMapping()
    public EntityResult addCategory(@RequestBody @Validated EntityCategory category) {
        return serviceCategory.addCategory(category);
    }

    /**
     * 获取文章分类列表
     * @return
     */
    @GetMapping()
    public EntityResult<List<EntityCategory>> list(){
        return serviceCategory.list();
    }

    /**
     * 获取分类详情
     * @return
     */
    @GetMapping("/detail")
    public EntityResult getDetail(@RequestParam Integer id){
        return serviceCategory.getDetail(id);
    }

    /**
     * 更新分类
     * @param category
     * @return
     */
    @PutMapping("/update")
    public EntityResult update(@RequestBody @Validated DtoUpdateCategory category){
        serviceCategory.update(category);
        return EntityResult.success();
    }

    /**
     * 删除文章分类
     * @param id
     * @return
     */
    @DeleteMapping
    public EntityResult delete(@RequestParam Integer id){
        serviceCategory.deleteById(id);
        return EntityResult.success();
    }

}
