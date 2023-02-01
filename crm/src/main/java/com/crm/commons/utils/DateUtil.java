package com.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: zr
 * Date: 2022/12/26 11:51
 * Description:
 */
public class DateUtil {
    public static String formatDateTime(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
