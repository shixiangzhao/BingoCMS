package com.shixzh.bcms.framework.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 除非与时间直接相关，否则不要用时间作为判断条件。
 * 比如，截止时间，是否到期这些是和时间直接相关，像是否送券，这些和时间不是直接相关。
 */
public class DateTest {
    public static void main(String[] args) throws ParseException {
        String lastDay = "2018-07-30";
        printDate(lastDay, true);
        printDate(lastDay, false);
    }

    private static void printDate(String lastDay, boolean isAddOne) throws ParseException {
        int[] period = {1, 3, 6};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(lastDay);
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < period.length; i++) {
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, period[i]);
            if (isAddOne) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            if (i == period.length - 1) {
                System.out.print("\'" + simpleDateFormat.format(calendar.getTime()) + "\'");
            } else {
                System.out.print("\'" + simpleDateFormat.format(calendar.getTime()) + "\',");
            }
        }
        if (isAddOne) {
            System.out.println("(actual)");
        } else {
            System.out.println("(expect)");
        }
    }
}
