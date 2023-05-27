package com.xiaomi.webproject.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    //日期转字符串
    public static String dateToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }
}
