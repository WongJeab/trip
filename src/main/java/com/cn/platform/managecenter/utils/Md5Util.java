package com.cn.platform.managecenter.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dhj on 2018/7/5.
 */
public class Md5Util {
    public static void main(String[] args) {
        System.out.println(Md5Util.MD5Encode("123456"));
        System.out.println(Md5Util.MD5Encode("123456",2));
    }
    public static  String MD5Encode(String  enStr){
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(enStr.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static  String MD5Encode(String  enStr,int times){
        String tempStr = enStr;
        for (int i=0;i<times;i++) {
            tempStr = MD5Encode(tempStr);
        }
       return tempStr;
    }

}
