package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageMapper storageMapper;

    @Override
    public int create(Storage storage) {
        return storageMapper.create(storage);
    }

    @Override
    public int selectLastId() {
        return storageMapper.selectLastId();
    }

    @Override
    public Map<String, Object> list(int page, int limit, String sort, String order, String key, String name) {
        //分页处理
        PageHelper.startPage(page,limit);
        Map<String,Object> map = new HashMap<>();

        //对象名称 对象KEY 都采用模糊查询
        //一个为空则 只按照一个来查询 两个都为空则返回 所有数据 两个都不为空时 &逻辑来查询

        int total = 0;
        List<Storage> list = new ArrayList<>();
        if( (key == null && name == null) || ("".equals(key) && "".equals(name)) ){
            //key和name都为空 查询所有数据
            list = storageMapper.selectAll();
            total = storageMapper.selectCountId();
        }else if( (key!=null && name==null) || (key!=null && "".equals(name)) ){
            //根据key模糊查询
            list = storageMapper.selectByKey("%" + key + "%");
            total = storageMapper.selectCountIdByKeyLike("%" + key + "%");
        }else if( name!=null && key==null || name!= null && "".equals(key) ){
            //根据name模糊查询
            list = storageMapper.selectByName("%" + name + "%");
            total = storageMapper.selectCountIdByNameLike("%" + name + "%");
        }else if( key != null && name !=null && !"".equals(key) && !"".equals(name)){
            //根据key和name模糊查询
            list = storageMapper.selectByKeyAndName("%" + key + "%","%" + name + "%");
            total = storageMapper.selectCountIdByKeyAndNameLike("%" + key + "%","%" + name + "%");
        }

        PageInfo<Storage> objectPageInfo = new PageInfo<>(list);

        map.put("total",total);
        map.put("items",list);
        return map;
    }

    @Override
    public int deleteStorageById(Integer id) {
        return storageMapper.deleteStorageById(id);
    }

    @Override
    public Storage update(Storage storage) {
        //更新逻辑:根据id 修改update_time 和 name
        Date date = new Date();
        storage.setUpdateTime(date);
        int update = storageMapper.update(storage);
        Storage resultStorage = null;
        if(update != 0){
            resultStorage = storageMapper.selectStorageById(storage.getId());
        }
        return resultStorage;
    }
}
