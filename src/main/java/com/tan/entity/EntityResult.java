package com.tan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityResult<T> {
    private Integer code;//业务状态码  0-成功  1-失败
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> EntityResult<E> success(E data) {
        return new EntityResult<>(0, "操作成功", data);
    }

    //快速返回操作成功响应结果
    public static EntityResult success() {
        return new EntityResult(0, "操作成功", null);
    }

    public static EntityResult error(String message) {
        return new EntityResult(1, message, null);
    }
}
