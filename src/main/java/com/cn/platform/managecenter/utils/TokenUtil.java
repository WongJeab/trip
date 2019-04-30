package com.cn.platform.managecenter.utils;

import java.util.UUID;

/**
 * User: wangyingxian
 * Date: 2019/04/25 11:13
 */
public class TokenUtil {


    //接口Api Token 依赖登录Token
    public static String getApiToken(long id,long sysTime,String uuid,String secret){
        String enStr = uuid+sysTime+id+secret;
        return  Md5Util.MD5Encode(enStr,2);
    }

    /*获取的登录*/
    public static String getLoginToken(long id,long sysTime,String uuid){
        long expire_time = 30*24*3600;//30d
        String enStr = uuid+sysTime+id+expire_time;
        return  Md5Util.MD5Encode(enStr);
    }


    /*获取加密密匙*/
    public static String getApiSecret(String uuid){
        return  Md5Util.MD5Encode(uuid);
    }

    /*UUID*/
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return  uuid;
    }

    /*获取系统时间*/
    public static long getSysTime(){
        long sysTime = System.currentTimeMillis();
        return  sysTime;
    }

    public static void main(String[] args) {
        long sysTime = getSysTime();
        String uuid = getUUID();
        System.out.println(uuid);
        String loginToken = TokenUtil.getLoginToken(1,sysTime,uuid);
        System.out.println(loginToken);
        String apiSecret = TokenUtil.getApiSecret(uuid);
        System.out.println(apiSecret);
        String apiToken = TokenUtil.getApiToken(1,sysTime,uuid,apiSecret);
        System.out.println(apiToken);
    }


}
