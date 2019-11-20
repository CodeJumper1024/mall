package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.StorageService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.awt.image.BandedSampleModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("admin/storage/")
@RequiresPermissions(value = {"admin:storage"})
public class StorageController {

    @Autowired
    StorageService storageService;

    @RequestMapping("create")
    public BaseReqVo create(MultipartFile file, HttpServletRequest request) throws IOException {

        File system = new File("target/classes/static");
        if(!system.exists()){
            system.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        //生成随机文件名
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString().replace("-", "");
        //key 对象关键字
        String key = s + originalFilename.substring(originalFilename.indexOf("."));

        //上传文件
        String absolutePath = system.getAbsolutePath();
        File path = new File(absolutePath + "/" + key);
        file.transferTo(path);


        //封装数据
        Storage storage = new Storage();
        //key 文件key
        storage.setKey(key);
        //name 文件原始名
        storage.setName(originalFilename);
        //size 文件大小
        storage.setSize((int) file.getSize());
        //type 文件类型
        storage.setType(file.getContentType());
        //url
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        int i = system.toString().lastIndexOf("\\");
        String substring = system.toString().substring(i + 1);
        substring.replace("\\","/");
        String url = "http://" + serverName + ":" + serverPort + "/" + substring +"/" + key;
        storage.setUrl(url);
        //下面是addTime和updateTime
        Date date = new Date();
        //addTime
        storage.setAddTime(date);
        //updateTime
        storage.setUpdateTime(date);

        //信息入库
        int insert = storageService.create(storage);

        //获得入库后的id 最后一次插入表格的id
        int id = storageService.selectLastId();

        storage.setId(id);

        //封装BaseReqVo并返回
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(storage);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("list")
    public BaseReqVo list(int page, int limit, String sort, String order,String key,String name){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        Map<String,Object> map = storageService.list(page,limit,sort,order,key,name);
        baseReqVo.setErrno(0);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Storage storage){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        Integer id = storage.getId();
        int delete = storageService.deleteStorageById(id);
        if(delete != 0){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Storage storage){
        BaseReqVo baseReqVo = new BaseReqVo<>();
        Storage resultStorage = storageService.update(storage);
        if(resultStorage != null){
            baseReqVo.setErrmsg("成功");
            baseReqVo.setData(resultStorage);
            baseReqVo.setErrno(0);
        }
        return baseReqVo;
    }

}
