package com.kh.openai.framework.assistant.core.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;

public class DateUtil {

    /**
     * 通过时间戳格式化
     * @param timeStamp 时间戳
     * @return yyyy-mm-dd hh:mm:ss
     */
    public static String formatDateByTimeStamp(Long timeStamp){
        if(ObjectUtil.isEmpty(timeStamp)){
            return "";
        }
        DateTime date = cn.hutool.core.date.DateUtil.date(timeStamp * 1000);
        return date.toString();
    }
}
