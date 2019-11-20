package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;
    @Override
    public BaseReqVo list(Integer page,Integer limit, String sort, String order,Integer id,String name) {
        BaseReqVo baseReqVo=new BaseReqVo();
        //分页工具
        PageHelper.startPage(page, limit);
        List<Brand> brandList=null;
        brandList = brandMapper.selectAllBrand(id,name);
        PageInfo<Brand> brandPageInfo=new PageInfo<>(brandList);
        long total = brandPageInfo.getTotal();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("items",brandList);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @Override
    public BaseReqVo createBrand(Brand brand) {
        BaseReqVo baseReqVo=new BaseReqVo();
        brandMapper.insertBrand(brand);
        int id=brand.getId();
        brand = brandMapper.selectByPrimaryKey(id);
        baseReqVo.setData(brand);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @Override
    public BaseReqVo updateBrand(Brand brand) {
        BaseReqVo baseReqVo=new BaseReqVo();
        brandMapper.updateBrandById(brand);
        int id=brand.getId();
        brand = brandMapper.selectByPrimaryKey(id);
        baseReqVo.setData(brand);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @Override
    public BaseReqVo deleteBrand(Brand brand) {
        BaseReqVo baseReqVo=new BaseReqVo();
        int a = brandMapper.deleteByPrimaryKey(brand.getId());
        if(a>0) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        else{
            baseReqVo.setErrno(1000);
            baseReqVo.setErrmsg("失败");
        }
        return baseReqVo;
    }

    @Override
    public BaseReqVo listWxBrand(Integer page, Integer size) {
        BaseReqVo baseReqVo=new BaseReqVo();
        //分页工具
        PageHelper.startPage(page,size);
        List<Brand> brandList=null;
        brandList = brandMapper.selectAll();
        PageInfo<Brand> brandPageInfo=new PageInfo<>(brandList);
        long total = brandPageInfo.getTotal();
        long totalPages=total/size;
        Map<String,Object> map=new HashMap<>();
        map.put("totalPages",totalPages);
        map.put("brandList",brandList);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @Override
    public BaseReqVo detailWxBrand(Integer id) {
        BaseReqVo baseReqVo=new BaseReqVo();
        Brand brand = new Brand();
        brand=brandMapper.selectByPrimaryKey(id);
        Map<String,Object> brandMap=new HashMap<>();
        brandMap.put("brand",brand);
        baseReqVo.setData(brandMap);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
