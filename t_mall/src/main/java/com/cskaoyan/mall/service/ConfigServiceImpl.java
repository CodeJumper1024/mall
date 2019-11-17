package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Mall;
import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.bean.SystemExample;
import com.cskaoyan.mall.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ConfigServiceImpl implements ConfigService{
    @Autowired
    SystemMapper systemMapper;

    @Override
    public int updateMall(Mall mall) {
        Date date = new Date();
        System system = new System();

        if(mall.getLitemall_mall_address()!=null && mall.getLitemall_mall_name()!=null &&
                mall.getLitemall_mall_phone()!=null && mall.getLitemall_mall_qq()!=null){
            system.setUpdateTime(date);
            SystemExample addressExample = new SystemExample();
            addressExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_address");
            system.setKeyValue(mall.getLitemall_mall_address());
            systemMapper.updateByExampleSelective(system,addressExample);

            SystemExample nameExample = new SystemExample();
            nameExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_name");
            system.setKeyValue(mall.getLitemall_mall_name());
            systemMapper.updateByExampleSelective(system,nameExample);

            SystemExample phoneExample = new SystemExample();
            phoneExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_phone");
            system.setKeyValue(mall.getLitemall_mall_phone());
            systemMapper.updateByExampleSelective(system,phoneExample);

            SystemExample qqExample = new SystemExample();
            qqExample.createCriteria().andKeyNameEqualTo("cskaoyan_mall_mall_qq");
            system.setKeyValue(mall.getLitemall_mall_qq());
            systemMapper.updateByExampleSelective(system,qqExample);
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Mall queryMall() {
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andKeyNameLike("cskaoyan_mall_mall%");
        List<System> systemList = systemMapper.selectByExample(systemExample);
        Mall mall = new Mall();
        for(System system : systemList){
            if("cskaoyan_mall_mall_address".equals(system.getKeyName())){
                mall.setLitemall_mall_address(system.getKeyValue());
            }if("cskaoyan_mall_mall_name".equals(system.getKeyName())){
                mall.setLitemall_mall_name(system.getKeyValue());
            }if("cskaoyan_mall_mall_phone".equals(system.getKeyName())){
                mall.setLitemall_mall_phone(system.getKeyValue());
            }if("cskaoyan_mall_mall_qq".equals(system.getKeyName())){
                mall.setLitemall_mall_qq(system.getKeyValue());
            }
        }
        return mall;
    }
}
