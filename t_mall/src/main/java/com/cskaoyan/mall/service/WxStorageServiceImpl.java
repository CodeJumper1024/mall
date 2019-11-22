package com.cskaoyan.mall.service;

import com.aliyun.oss.OSSClient;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.component.AliyunComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Service
public class WxStorageServiceImpl implements WxStorageService {

    @Autowired
    AliyunComponent aliyunComponent;

    @Override
    public BaseReqVo upload(MultipartFile file) {
        if (file != null){
            //文件名
            String name = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            //指定文件夹
            File filePath = new File("target/classes/static");
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            String absolutePath = filePath.getAbsolutePath();
            File saveFile = new File(absolutePath + "/" + name);
            try {
                file.transferTo(saveFile);
                OSSClient ossClient = aliyunComponent.getOssClient();
                ossClient.putObject(aliyunComponent.getOss().getBucket(), name, saveFile);
                String url = aliyunComponent.getOss().getBucket() + "." + aliyunComponent.getOss().getEndPoint() + "/" + name;
                BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("hasPicture", true);
                dataMap.put("picUrl", url);
                baseReqVo.setErrno(0);
                baseReqVo.setErrmsg("成功");
                baseReqVo.setData(dataMap);
                return baseReqVo;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

