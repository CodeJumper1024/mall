package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Category;

public interface CategoryService {
    BaseReqVo list();

    BaseReqVo createCategory(Category category);

    BaseReqVo listFirst();

    BaseReqVo updateCategory(Category category);

    BaseReqVo deleteCategory(Category category);
}
