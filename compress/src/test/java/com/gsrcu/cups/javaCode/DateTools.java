package com.gsrcu.cups.javaCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Author ThinkPad
 * Created : 2019-03-11 19:16
 * @Since
 */
public class DateTools {
    /**
     * 获取当前星期的第一天日期
     * 已周一为一个星期的第一天
     */
    public static String getFirstDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        return getFirstDayOfWeek(calendar);
    }
    /**
     * 获取制定星期的第一天日期
     * 已周一为一个星期的第一天
     * @param date 日期  格式为 yyyyMMdd
     */
    public static String getFirstDayOfWeek(String date){
        return getFirstDayOfWeek(getCalendar(date));
    }

    private static String getFirstDayOfWeek(Calendar calendar){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int dayofweek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        dayofweek = dayofweek == 0 ? 7 : dayofweek;
        calendar.add(Calendar.DAY_OF_WEEK , -(dayofweek-1));
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 获取当前季度的第一天
     * @return
     */
    public static String getFirstDayofQuarter(){
        return getFirstDayofQuarter(Calendar.getInstance());
    }
    /**
     * 获取制定日期季度的第一天
     * @param date 日期  格式为yyyyMMdd
     * @return
     */
    public static String getFirstDayofQuarter(String date){
        return getFirstDayofQuarter(getCalendar(date));
    }
    private static String getFirstDayofQuarter(Calendar calendar){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMM01");
        calendar.add(Calendar.MONTH,-calendar.get(Calendar.MONTH)%3);
        return dateFormat.format(calendar.getTime());
    }

    private static Calendar getCalendar(String date){
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6))-1;
        int day = Integer.parseInt(date.substring(6,8));
        calendar.set(year,month,day);
        return calendar;
    }

    public static void main(String[] args){
//        System.out.println(DateTools.getFirstDayofQuarter("20190104"));
        Calendar calendar = getCalendar("20191301");
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(getFirstDayofQuarter("20190501"));
    }
}
