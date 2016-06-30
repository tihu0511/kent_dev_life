package org.jigang.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BF100177 on 2016/6/17.
 */
public class DateUtil {

    public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    public static String parseDateToString(Date date){
        return new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS).format(date);
    }

    public static Date parse(String date) throws ParseException {
        return  new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS).parse(date);
    }

    public static Date parse(String date, String format) throws ParseException {
        return  new SimpleDateFormat(format).parse(date);
    }

    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取今天的日期
     * @return
     */
    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 增加日期,days可以为负数
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
}
