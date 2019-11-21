package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {

    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectAll();

    List<Category> selectByPid(@Param("id") int pid);

    List<Category> selectAllCat();

    int insertAll(@Param("category") Category category);

    int updateCategoryById(@Param("category") Category category);

    int deleteById(@Param("id") Integer id);

    List<Category> selectAllLeve1();

    Category queryCurrentCategoryL1(@Param("id") Integer id);

    List<Category> queryBrotherCategoryL1(@Param("id") Integer id);

    Category queryParentCategoryL1(@Param("id") Integer id);

    Category queryCurrentCategoryL2(@Param("id") Integer id);

    List<Category> queryBrotherCategoryL2(@Param("pid") Integer pid);

    Category queryParentCategoryL2(@Param("pid") Integer pid);

    int queryPidById(@Param("id") Integer id);

    List<Category> queryCategoryByLv1Id(Integer id);

    Category queryCategoryById(Integer id);

    List<Category> queryL2Categories();

    Category queryCategoriesByCid(@Param("cid") Integer cid);

    String queryLevelById(@Param("id") Integer id);

    List<Category> selectAllLeve2();

    Integer[] selectLevel2ByPid(@Param("pid") Integer pid);
}
