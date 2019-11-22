package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    //分页获取商品
    @Override
    public BaseReqVo list(Integer page, Integer limit, String sort, String order, String name, Long goodsSn) {
        BaseReqVo<Map> baseReqVo = new BaseReqVo<>();
        //分页工具
        PageHelper.startPage(page, limit);
        //根据条件查询商品
        List<Goods> goodsList = goodsMapper.selectGoodsByQueryCondition(sort, order, "%" + name + "%", goodsSn);
        //获取查询数目
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", total);
        dataMap.put("items", goodsList);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //显示商品详情
    @Override
    public BaseReqVo detail(int id) {
        BaseReqVo<Map> baseReqVo = new BaseReqVo<>();
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        int[] categoryIds = {goods.getCategoryId()};
        List<GoodsAttribute> attributes = goodsAttributeMapper.selectByGoodsId(id);
        List<GoodsProduct> products = goodsProductMapper.selectByGoodsId(id);
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectByGoodsId(id);
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("categoryIds", categoryIds);
        dataMap.put("goods", goods);
        dataMap.put("attributes", attributes);
        dataMap.put("specifications", specifications);
        dataMap.put("products", products);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    //获取所有分类和品牌信息
    @Override
    public BaseReqVo catAndBrand() {
        BaseReqVo<Map> baseReqVo = new BaseReqVo<>();

        List<Category> categories = categoryMapper.selectAllCat();
        ArrayList<Category> listL1 = new ArrayList<>();
        for (Category category : categories) {
            if (category.getLevel().equals("L1")){
                listL1.add(category);
            }
        }
        ArrayList<Map> categoryList = new ArrayList<>();
        for (Category category : listL1) {
            HashMap<String, Object> categoryListMap = new HashMap<>();
            categoryListMap.put("value", category.getId());
            categoryListMap.put("label", category.getName());
            ArrayList<Map> children = new ArrayList<>();
            HashMap<String, Object> childrenMap = null;
            for (Category category1 : categories) {
                if ((category1.getSortOrder() == category.getSortOrder()) && (category1.getId() != category.getId())){
                    childrenMap = new HashMap<>();
                    childrenMap.put("value", category1.getId());
                    childrenMap.put("label", category1.getName());
                    children.add(childrenMap);
                }
            }
            categoryListMap.put("children", children);
            categoryList.add(categoryListMap);
        }
        List<Brand> brands = brandMapper.selectAllBrandNoParm();
        ArrayList<Map> brandList = new ArrayList<>();
        for (Brand brand : brands) {
            HashMap<String, Object> brandMap = new HashMap<>();
            brandMap.put("value", brand.getId());
            brandMap.put("label", brand.getName());
            brandList.add(brandMap);
        }
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("categoryList", categoryList);
        dataMap.put("brandList", brandList);
        baseReqVo.setErrno(0);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    //更新商品信息
    @Override
    public BaseReqVo update(Map<String, Object> info) {
        //获取更新时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = format.format(date);
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        if (info.get("goods") != null){
            goodsMapper.updateByGoodsMap((Map)(info.get("goods")), updateTime);
        }
        if ((List)(info.get("specifications")) != null){
            List specifications = (List)(info.get("specifications"));
            for (Object specification : specifications) {
                goodsSpecificationMapper.updateBySpecificationMap((Map)(specification), updateTime);
            }
        }
        if (info.get("products") != null){
            List products = (List)(info.get("products"));
            for (Object product : products) {
                 goodsProductMapper.updateByProductMap((Map)product, updateTime);
            }
        }
        if (info.get("attributes") != null){
            List attributes = (List)(info.get("attributes"));
            for (Object attribute : attributes) {
                goodsAttributeMapper.updateByAttributeMap((Map)attribute, updateTime);
            }
        }
        baseReqVo.setData(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    //删除商品
    @Override
    public BaseReqVo delete(Map<String, Object> info) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        String g_id = (String)info.get("goodsSn");
        int goodsId = Integer.parseInt(g_id);
        goodsMapper.deleteByGoodsId(goodsId);
        goodsSpecificationMapper.deleteByGoodsId(goodsId);
        goodsAttributeMapper.deleteByGoodsId(goodsId);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    //增加商品
    @Override
    public BaseReqVo create(Map<String, Object> info) {
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        //获取新增时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addTime = format.format(date);
        Map<String, Object> goodsMap = (Map)(info.get("goods"));
        goodsMap.put("id", 1);
        int result = goodsMapper.insertByGoodsMap(goodsMap, addTime);
        int goodsId = (Integer) goodsMap.get("id");
        String gallery = "";
        String shareUrl = "";
        goodsMapper.updateInfoOfLastInsert(goodsId, gallery, shareUrl);
        List<Map> specifications = (List)(info.get("specifications"));
        for (Map specification : specifications) {
            goodsSpecificationMapper.insertBySpecificationMap(specification, addTime, goodsId);
        }
        List<Map> products = (List)(info.get("products"));
        for (Map product : products) {
            goodsProductMapper.insertByProductMap(product, addTime, goodsId);
        }
        List<Map> attributes = (List)(info.get("attributes"));
        for (Map attribute : attributes) {
            goodsAttributeMapper.insertByAttributeMap(attribute, addTime, goodsId);
        }
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @Override
    public Goods queryGoodsByGoodsId(Integer goodsId) {
        return goodsMapper.selectByGoodsId(goodsId);
    }


    @Override
    public Goods queryGoods(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

}
