package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public BaseReqVo list() {
        List<Map> list=new ArrayList<>();
        BaseReqVo baseReqVo = new BaseReqVo();;
        List<Category> categories=categoryMapper.selectAll();
        for (Category category : categories) {
            int pid=category.getId();
            Map<String,Object> categorymap=new HashMap<>();
            categorymap.put("id",category.getId());
            categorymap.put("name",category.getName());
            categorymap.put("iconUrl",category.getIconUrl());
            categorymap.put("picUrl",category.getPicUrl());
            categorymap.put("keywords",category.getKeywords());
            categorymap.put("level",category.getLevel());
            categorymap.put("desc",category.getDesc());
            List<Category> categoryList=categoryMapper.selectByPid(pid);
            category.setCategories(categoryList);
            categorymap.put("children",category.getCategories());
            list.add(categorymap);
        }

        baseReqVo.setData(list);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
