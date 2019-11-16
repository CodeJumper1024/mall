package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/stat")
public class StatController {

    @Autowired
    StatService statService;

    @RequestMapping("user")
    public BaseReqVo userStat() {

        Date addTime = statService.queryLatestAddTime();
        int users = statService.queryUsersByAddTime();

        ArrayList<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("users");

        HashMap<String, Object> map = new HashMap<>();
        map.put("day", addTime);
        map.put("users", users);
        ArrayList<Map> rows = new ArrayList<>();
        rows.add(map);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("columns", columns);
        dataMap.put("rows", rows);

        BaseReqVo<Object> listBaseReqVo = new BaseReqVo<>();
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(dataMap);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
