package com.example.deveshwar.charliesearch.data;


import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by devashish.sharma on 12/1/2015.
 */
public class QueryContract {

    public static long normalizeDate(long startDate){
        Time time = new Time();
        time.set(startDate);
        int myDay = Time.getJulianDay(startDate,time.gmtoff);
        return time.setJulianDay(myDay);
    }

    public static final class QueryEntry implements BaseColumns{
        public static final String TABLE_NAME = "data";
        public static final String COLUMN_QUERY ="query";
        public static final String COLUMN_DATE_TIME = "date time";
    }
}
