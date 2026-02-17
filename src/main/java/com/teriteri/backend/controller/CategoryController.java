package com.teriteri.backend.controller;

import com.teriteri.backend.pojo.CustomResponse;
import com.teriteri.backend.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取全部分区接口

 * 该接口用于获取所有的分区信息
     * @return CustomResponse对象 包含所有分区信息的响应对象
     */
    @GetMapping("/category/getall")  // HTTP GET请求映射，访问路径为/category/getall
    public CustomResponse getAll() {  // 定义获取所有分区的方法
        return categoryService.getAll();  // 调用categoryService的getAll方法获取所有分区信息并返回
    }
}
