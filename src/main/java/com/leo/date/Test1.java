package com.leo.date;

import com.leo.view.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Test1 {
    public static void main(String[] args) throws ParseException {
        String time = "2020-01-31 00:00:00";
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(getMillSeconds(time));
        System.out.println(calendar.getTime());

        calendar.set(Calendar.MONTH, 1);
        System.out.println(calendar.getTime());

        System.out.println(calendar.get(Calendar.MONTH));
    }


    public static long getMillSeconds(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.parse(time).getTime();


    }


}
