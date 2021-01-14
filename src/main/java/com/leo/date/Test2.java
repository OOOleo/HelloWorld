package com.leo.date;



import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class Test2 {
    @Test
    public void main() {
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());

        int month = calendar.get(Calendar.MONTH);
        System.out.println(month);


    }
}
