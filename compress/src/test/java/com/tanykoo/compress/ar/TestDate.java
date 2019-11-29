package com.tanykoo.compress.ar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;

/**
 * @Author ThinkPad
 * Created : 2019-03-11 18:24
 * @Since
 */
public class TestDate {
    private static Log logger = LogFactory.getLog(TestDate.class);

    public static void main(String[] args){
//        System.out.println(DateTools.getFirstDayofQuarter("20190104"));
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MONTH));
    }
}
