package com.cskaoyan.mall.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    public static String getMd5(String content){
        //将密码字符串转换为二进制数组(每个字符对应的编码)
        byte[] bytes = content.getBytes();
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //获取密码的摘要数组(128bit)
            byte[] digest = md5.digest(bytes);
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                //去掉正负
                int x = b & 0xff;
                //转换为16进制字符串
                String s = Integer.toHexString(x);
                if (s.length() == 1){
                    sb.append(0);
                }
                sb.append(s);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getMd5(String content,String sault){
        content = content + "{11111" + sault;
        String md5 = getMd5(content);
        return md5;
    }

    public static String getMultiMd5(String content){
        String md5 = getMd5(content);
        String md51 = getMd5(md5);
        String md52 = getMd5(md51);
        String md53 = getMd5(md5, md52);
        return md53;
    }

    public static String getFileMd5(File file){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fileInputStream.read(bytes,0,1024)) > 0){
                md5.update(bytes,0,length);
            }
            byte[] digest = md5.digest(); //16

            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                int x = b & 0xff; // 0a = 10 a0 = 160
                //16进制的字符串00 - FF 255 16* 15 + 15
                String s = Integer.toHexString(x); //a 0
                if (s.length() == 1){
                    sb.append(0); //补位
                }
                sb.append(s);
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
