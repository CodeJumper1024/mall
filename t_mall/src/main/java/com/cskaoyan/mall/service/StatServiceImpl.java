package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.GoodsStatInfo;
import com.cskaoyan.mall.bean.OrderStatInfo;
import com.cskaoyan.mall.bean.UserStatInfo;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public BaseReqVo goodsStat() {
        BaseReqVo<Map> goodsBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> dataMap = new HashMap<>();
        String[] columns = {"day", "orders", "products", "amount"};
        dataMap.put("columns", columns);
        List<GoodsStatInfo> infos = orderGoodsMapper.queryGoodsStatInfo();
        ArrayList<Map> rows = new ArrayList<>();
        for (GoodsStatInfo info : infos) {
            HashMap<String, Object> rowMap = new HashMap<>();
            rowMap.put("amount", info.getAmount());
            rowMap.put("orders", info.getOrders());
            rowMap.put("day", info.getDay());
            rowMap.put("products", info.getProducts());
            rows.add(rowMap);
        }
        dataMap.put("rows", rows);
        goodsBaseReqVo.setErrno(0);
        goodsBaseReqVo.setData(dataMap);
        goodsBaseReqVo.setErrmsg("成功");
        return goodsBaseReqVo;
    }

    @Override
    public BaseReqVo userStat() {
        BaseReqVo<Map> userBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> data = new HashMap<>();
        String[] columns = {"day", "users"};
        data.put("columns", columns);
        List<UserStatInfo> infos = userMapper.queryUserStatInfo();
        ArrayList<Map> rows = new ArrayList<>();
        for (UserStatInfo info : infos) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("day", info.getDay());
            map.put("users", info.getUsers());
            rows.add(map);
        }
        data.put("rows", rows);
        userBaseReqVo.setErrno(0);
        userBaseReqVo.setData(data);
        userBaseReqVo.setErrmsg("成功");
        return userBaseReqVo;
    }

    @Override
    public BaseReqVo orderStat() {
        BaseReqVo<Map> orderBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> data = new HashMap<>();
        String[] columns = {"day", "orders", "customers", "amount", "pcr"};
        ArrayList<Map> rows = new ArrayList<>();
        List<OrderStatInfo> infos = orderMapper.queryOrderStatInfo();
        for (OrderStatInfo info : infos) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("amount", info.getAmount());
            map.put("orders", info.getOrders());
            map.put("customers", info.getCustomers());
            map.put("day", info.getDay());
            map.put("pcr", info.getPcr());
            rows.add(map);
        }
        data.put("columns", columns);
        data.put("rows", rows);
        orderBaseReqVo.setErrno(0);
        orderBaseReqVo.setData(data);
        orderBaseReqVo.setErrmsg("成功");
        return orderBaseReqVo;
    }
}
