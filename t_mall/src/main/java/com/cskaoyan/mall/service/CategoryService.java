package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Category;

import java.util.List;

public interface CategoryService {
    BaseReqVo list();

    BaseReqVo createCategory(Category category);

    BaseReqVo listFirst();

    BaseReqVo updateCategory(Category category);

    BaseReqVo deleteCategory(Category category);

    List<Category> queryCategoryByLv1();

    List<Category> queryCategoryByLv1Id(Integer id);

    Category queryCategoryById(Integer id);
}
