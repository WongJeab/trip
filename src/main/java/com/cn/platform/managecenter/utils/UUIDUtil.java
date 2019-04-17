package com.cn.platform.managecenter.utils;

import java.util.UUID;

/**
 * Created by dhj on 2018/7/5.
 */
public class UUIDUtil {
    public static String  getUUID(){
        return  UUID.randomUUID().toString().replace("-","");

    }

    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            System.out.println(getUUID());
        }

    }
}
