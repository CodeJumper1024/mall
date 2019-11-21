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
    @Override
    public BaseReqVo listFirst() {
        List<Map> list=new ArrayList<>();
        BaseReqVo baseReqVo = new BaseReqVo();;
        List<Category> categories=categoryMapper.selectAll();
        for (Category category : categories) {
            Map<String,Object> categorymap=new HashMap<>();
            categorymap.put("value",category.getId());
            categorymap.put("label",category.getName());
            list.add(categorymap);
        }
        baseReqVo.setData(list);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


    @Override
    public BaseReqVo createCategory(Category category) {
        if(category.getPid()==0&&category.getLevel().equals("L2")){
            category.setLevel("L1");
        }
        BaseReqVo baseReqVo=new BaseReqVo();
        int i=categoryMapper.insertAll(category);
        if(i>0) {
            int id = category.getId();
            category = categoryMapper.selectByPrimaryKey(id);
            baseReqVo.setData(category);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }else{
            baseReqVo.setData(category);
            baseReqVo.setErrno(1001);
            baseReqVo.setErrmsg("很失败");
        }
        return baseReqVo;
    }

    @Override
    public BaseReqVo updateCategory(Category category) {
        BaseReqVo baseReqVo=new BaseReqVo();
//        Category category1=categoryMapper.selectByPrimaryKey(category.getId());
        if(category.getChildren()==null||category.getChildren().isEmpty()){
            if((category.getPid().intValue()==category.getId().intValue())&&category.getLevel().equals("L2")){
                baseReqVo.setData(category);
                baseReqVo.setErrno(508);
                baseReqVo.setErrmsg("不能把自己设成二级目录并且存入自己中");
            }else {
                int i = categoryMapper.updateCategoryById(category);
                if (i > 0) {
                    int id = category.getId();
                    category = categoryMapper.selectByPrimaryKey(id);
                    baseReqVo.setData(category);
                    baseReqVo.setErrno(0);
                    baseReqVo.setErrmsg("成功");
                } else {
                    baseReqVo.setData(category);
                    baseReqVo.setErrno(1002);
                    baseReqVo.setErrmsg("很失败");
                }
            }
        }else if((category.getPid().intValue()==category.getId().intValue())&&category.getLevel().equals("L2")){
            baseReqVo.setData(category);
            baseReqVo.setErrno(508);
            baseReqVo.setErrmsg("不能把自己设成二级目录并且存入自己中");
        } else{
            baseReqVo.setData(category);
            baseReqVo.setErrno(507);
            baseReqVo.setErrmsg("失败");
        }
        return baseReqVo;
    }

    @Override
    public BaseReqVo deleteCategory(Category category) {
        BaseReqVo baseReqVo=new BaseReqVo();
        if(category.getChildren()!=null){
            for (Category child : category.getChildren()) {
                int i=categoryMapper.deleteById(child.getId());
            }
        }
        int i=categoryMapper.deleteById(category.getId());
        if(i>0){
            baseReqVo.setData(category);
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }else{
            baseReqVo.setData(category);
            baseReqVo.setErrno(1003);
            baseReqVo.setErrmsg("删除失败");
        }
        return baseReqVo;
    }

    @Override
    public List<Category> queryCategoryByLv1() {
         return categoryMapper.selectAllLeve1();
    }

    @Override
    public List<Category> queryCategoryByLv1Id(Integer id) {
        return categoryMapper.queryCategoryByLv1Id(id);
    }

    @Override
    public Category queryCategoryById(Integer id) {
        return categoryMapper.queryCategoryById(id);
    }

    @Override
    public Category queryCategoriesByCid(Integer cid) {
        return categoryMapper.queryCategoriesByCid(cid);
    }
}
