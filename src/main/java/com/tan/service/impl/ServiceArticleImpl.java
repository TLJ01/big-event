package com.tan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tan.dto.DtoSaveArticle;
import com.tan.dto.DtoUpdateArticle;
import com.tan.entity.EntityArticle;
import com.tan.entity.EntityResult;
import com.tan.entity.PageBean;
import com.tan.mapper.MapperArticle;
import com.tan.service.ServiceArticle;
import com.tan.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Slf4j
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

    /**
     * 文章列表
     * @param currentPage
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    @Override
    public PageBean<EntityArticle> list(Integer currentPage, Integer pageSize, String categoryId, String state) {

        PageBean<EntityArticle> pageBean = new PageBean<>();

        PageHelper.startPage(currentPage,pageSize);
        //查询
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<EntityArticle> data =  mapperArticle.list(userId,categoryId,state);

        //log.info("数据:{}",data);


        Page<EntityArticle>page = (Page<EntityArticle>) data;
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());

        return pageBean;
    }

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @Override
    public EntityResult getDetail(Integer id) {
        EntityArticle entityArticle = mapperArticle.getDetail(id);
        return EntityResult.success(entityArticle);
    }

    /**
     * 更新文章
     * @param dtoUpdateArticle
     */
    @Override
    public void update(DtoUpdateArticle dtoUpdateArticle) {
        mapperArticle.updte(dtoUpdateArticle);
    }

    /**
     * 删除文章
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        mapperArticle.deleteById(id);
    }
}
