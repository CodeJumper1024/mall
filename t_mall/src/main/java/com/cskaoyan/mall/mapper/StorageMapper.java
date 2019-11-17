package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.StorageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StorageMapper {
    long countByExample(StorageExample example);

    int deleteByExample(StorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);

    List<Storage> selectByExample(StorageExample example);

    Storage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByExample(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    int create(@Param("storage") Storage storage);

    int selectLastId();

    //下面8个接口是一组多条件的模糊查询

    List<Storage> selectAll();

    int selectCountId();

    List<Storage> selectByKey(@Param("key") String key);

    int selectCountIdByKeyLike(@Param("key") String key);

    List<Storage> selectByName(@Param("name") String name);

    int selectCountIdByNameLike(@Param("name") String name);

    List<Storage> selectByKeyAndName(@Param("key") String key,@Param("name") String name);

    int selectCountIdByKeyAndNameLike(@Param("key") String key,@Param("name") String name);

    //上面8个接口是一组多条件的模糊查询

    int deleteStorageById(@Param("id")Integer id);

    int update(@Param("storage") Storage storage);

    Storage selectStorageById(@Param("id") Integer id);

}
