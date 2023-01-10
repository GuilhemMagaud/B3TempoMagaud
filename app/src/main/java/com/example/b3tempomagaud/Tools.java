package com.example.b3tempomagaud;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Tools {
    private static final String LOG_TAG = Tools.class.getSimpleName();
    private static AtomicInteger atomicInteger = null;
    private static final int INITIAL_GENERATOR_VALUE = 2023;
    // prevent object instanciation
    private Tools() {

    }
    /*
     * --- Helpers methods ---
     *
     */
    /**
     * getNowDate("yyyy") would return "2022" at the time this comment is written
     *
     * @param pattern : pattern to be used by the date formatter (see {@link SimpleDateFormat}
     * class for date pattern explanations)
     * @return the device locale date at the time of the call. The date is formatted using the
     * given date pattern
     */
    public static String getNowDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.FRANCE);
        Date now = new Date();
        return sdf.format(now);
    }

    public static String getTomorowDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.FRANCE);
        Date tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        return sdf.format(tomorrow);
    }

    public static int getNextNotifId() {
        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger(INITIAL_GENERATOR_VALUE);
            return atomicInteger.get();
        } else {
            return atomicInteger.incrementAndGet();
        }
    }

    public  static String formatTempoHistoryDate(String apiDate) {
        try {
            String[] ymdArray = apiDate.split("-");
            Date date = new Date(Integer.parseInt(ymdArray[0])-1900, Integer.parseInt(ymdArray[1])-1, Integer.parseInt(ymdArray[2]));
            SimpleDateFormat sdf = new SimpleDateFormat("E d MMM yyyy", Locale.FRANCE);
            return sdf.format(date);
        } catch (NumberFormatException e) {
            Log.w(LOG_TAG,e.getMessage());
            return "?";
        }
    }
}
