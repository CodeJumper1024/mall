package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Children;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.bean.RegionExample;
import com.cskaoyan.mall.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;
    @Override
    public BaseReqVo list() {
        List<Map> list=new ArrayList<>();
        BaseReqVo baseReqVo = new BaseReqVo();
        List<Region> regions=regionMapper.selectAll();
        for (Region region : regions) {
            int pid=region.getId();
            Map<String,Object> map=new HashMap<>();
            List<Children> children=regionMapper.selectByPid(pid);
            map.put("id",region.getId());
            map.put("name",region.getName());
            map.put("type",region.getType());
            map.put("code",region.getCode());
            for (Children child : children) {
                pid=child.getId();
                Map<String,Object> childmap=new HashMap<>();
                List<Children> children1 = regionMapper.selectByPid(pid);
                child.setChildren(children1);
            }
            region.setChildren(children);
            map.put("children",children);
            list.add(map);
        }

        baseReqVo.setData(list);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @Override
    public BaseReqVo listRegion(Integer pid) {
        BaseReqVo baseReqVo = new BaseReqVo();
        List<Region> regions=regionMapper.selectByPid1(pid);
        baseReqVo.setData(regions);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
