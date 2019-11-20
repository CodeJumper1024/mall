package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("wx/collect")
public class WxCollectController {

    @Autowired
    CollectService collectService;

    //@PostMapping("addordelete")
    public BaseReqVo addOrDelCollect(@RequestBody Collect collect) {
        Byte type = collectService.addOrDelCollect(collect.getValueId());

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("type", type);

        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(0);
        baseReqVo.setData(dataMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
