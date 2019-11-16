package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.BaseReqVo;
import com.cskaoyan.mall.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AdminMapper adminMapper;

    //登录
    @Override
    public BaseReqVo login(Admin admin) {
        BaseReqVo baseReqVo = new BaseReqVo();
        if (admin.getUsername() == null){
            baseReqVo.setErrmsg("参数不对");
            baseReqVo.setErrno(0);
        }else {
            /*//判断符合账号和密码的admin的个数
            List<Admin> admins = adminMapper.selectByUsernameAndPassword(admin);
            if (admins.size() == 0){
                baseReqVo.setErrmsg("用户账号和密码不正确");
                baseReqVo.setErrno(605);
            }else {
                baseReqVo.setData();
                baseReqVo.setErrmsg("成功");
                baseReqVo.setErrno(0);
            }*/
            baseReqVo.setData("78b72c8b-9fde-4227-b740-7cfa43612d62");
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
        }
        return baseReqVo;
    }
}
