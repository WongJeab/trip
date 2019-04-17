package com.cn.platform.managecenter.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    /**
     * String 转换 Integer
     * @param inString
     * @return
     */
    public static Integer[] stringParseInt(String inString){
        List<Integer> list = new ArrayList();
        String ids[] = inString.split(";");
        for (String id:ids) {
            list.add(Integer.parseInt(id));
        }
        return list.toArray(new Integer[list.size()]);
    }
}
