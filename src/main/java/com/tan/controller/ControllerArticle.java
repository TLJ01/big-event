package com.tan.controller;

import com.tan.dto.DtoSaveArticle;
import com.tan.dto.DtoUpdateArticle;
import com.tan.entity.EntityArticle;
import com.tan.entity.EntityResult;
import com.tan.entity.PageBean;
import com.tan.service.ServiceArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 文章列表
     * @return
     */
    @GetMapping
    public EntityResult<PageBean<EntityArticle>>list(
            Integer currentPage,
            Integer pageSize,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false)  String state){

        PageBean<EntityArticle> pageBean = serviceArticle.list(currentPage,pageSize,categoryId,state);
        return EntityResult.success(pageBean);
    }

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @GetMapping("detail")
    public EntityResult getDetail(Integer id) {
        return serviceArticle.getDetail(id);
    }

    /**
     * 更新
     * @param dtoUpdateArticle
     * @return
     */
    @PutMapping("/update")
    public EntityResult update(@RequestBody DtoUpdateArticle dtoUpdateArticle){
        serviceArticle.update(dtoUpdateArticle);
        return EntityResult.success();
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @DeleteMapping
    public EntityResult delete(Integer id) {
        serviceArticle.deleteById(id);
        return EntityResult.success();
    }

}
