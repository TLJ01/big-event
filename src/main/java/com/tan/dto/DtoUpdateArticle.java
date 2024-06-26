package com.tan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DtoUpdateArticle {
    @NotNull
    private Integer id;//主键ID
    @NotEmpty
    private String title;//文章标题
    @NotEmpty
    private String content;//文章内容
    @NotEmpty
    private String coverImg;//封面图像
    @NotEmpty
    private String state;//发布状态 已发布|草稿
    @NotEmpty
    private Integer categoryId;//文章分类id
}
