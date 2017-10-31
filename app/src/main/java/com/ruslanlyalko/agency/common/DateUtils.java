package com.ruslanlyalko.agency.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ruslan Lyalko
 * on 30.10.2017.
 */

public class DateUtils {
    public static String getDate(Date date) {
        if (date == null) return "-";
        return new SimpleDateFormat("dd.MM.yyyy", Locale.US).format(date);
    }
}
