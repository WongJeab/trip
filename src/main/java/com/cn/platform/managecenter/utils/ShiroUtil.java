package com.cn.platform.managecenter.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;


/**
 * Created by dhj on 2018/8/9.
 */
public class ShiroUtil {
    public static void main(String[] args) {
        System.out.println( "5e543256c480ac577d30f76f9120eb74");

        System.out.println(sha256Hash("5e543256c480ac577d30f76f9120eb74"));
    }



    public static String  sha256Hash(String pwd){
           String salt="sasms";

        String npwd= new Sha256Hash(pwd,salt,1024).toBase64();

         return  npwd;

    }
    public static String  md5Hash(String salt,String pwd) {

        String npwd = new Md5Hash(pwd, salt, 1024).toBase64();

        return npwd;
    }

    }
