package com.ido.life.util;

import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes3.dex */
public class SunRiseSetUtils {
    private static final double UTo = 180.0d;
    private static int[] days_of_month_1 = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static int[] days_of_month_2 = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final double f4694h = -0.833d;

    public static double G_sun(double d2) {
        return (d2 * 35999.05d) + 357.528d;
    }

    public static double L_sun(double d2) {
        return (d2 * 36000.77d) + 280.46d;
    }

    public static double UT_rise(double d2, double d3, double d4, double d5) {
        return d2 - ((d3 + d4) + d5);
    }

    public static double UT_set(double d2, double d3, double d4, double d5) {
        return d2 - ((d3 + d4) - d5);
    }

    public static double earth_tilt(double d2) {
        return 23.4393d - (d2 * 0.013d);
    }

    public static double t_century(int i, double d2) {
        return (((double) i) + (d2 / 360.0d)) / 36525.0d;
    }

    public static boolean leap_year(int i) {
        if (i % 400 != 0) {
            return i % 100 != 0 && i % 4 == 0;
        }
        return true;
    }

    public static int days(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 2000; i6 < i; i6++) {
            i5 = leap_year(i6) ? i5 + 366 : i5 + 365;
        }
        if (leap_year(i)) {
            while (i4 < i2 - 1) {
                i5 += days_of_month_2[i4];
                i4++;
            }
        } else {
            while (i4 < i2 - 1) {
                i5 += days_of_month_1[i4];
                i4++;
            }
        }
        return i5 + i3;
    }

    public static double ecliptic_longitude(double d2, double d3) {
        return d2 + (Math.sin((d3 * 3.141592653589793d) / UTo) * 1.915d) + (Math.sin(((d3 * 2.0d) * 3.141592653589793d) / UTo) * 0.02d);
    }

    public static double sun_deviation(double d2, double d3) {
        return Math.asin(Math.sin(d2 * 0.017453292519943295d) * Math.sin(d3 * 0.017453292519943295d)) * 57.29577951308232d;
    }

    public static double GHA(double d2, double d3, double d4) {
        return ((((d2 - UTo) - (Math.sin((d3 * 3.141592653589793d) / UTo) * 1.915d)) - (Math.sin(((d3 * 2.0d) * 3.141592653589793d) / UTo) * 0.02d)) + (Math.sin(((2.0d * d4) * 3.141592653589793d) / UTo) * 2.466d)) - (Math.sin(((d4 * 4.0d) * 3.141592653589793d) / UTo) * 0.053d);
    }

    public static double e(double d2, double d3, double d4) {
        double dSin = Math.sin((d2 * 3.141592653589793d) / UTo);
        double d5 = (d3 * 3.141592653589793d) / UTo;
        double dSin2 = Math.sin(d5);
        double d6 = (d4 * 3.141592653589793d) / UTo;
        return Math.acos((dSin - (dSin2 * Math.sin(d6))) / (Math.cos(d5) * Math.cos(d6))) * 57.29577951308232d;
    }

    public static double result_rise(double d2, double d3, double d4, double d5, int i, int i2, int i3) {
        if ((d2 >= d3 ? d2 - d3 : d3 - d2) < 0.1d) {
            return d2;
        }
        double dUT_rise = UT_rise(d2, GHA(d2, G_sun(t_century(days(i, i2, i3), d2)), ecliptic_longitude(L_sun(t_century(days(i, i2, i3), d2)), G_sun(t_century(days(i, i2, i3), d2)))), d4, e(f4694h, d5, sun_deviation(earth_tilt(t_century(days(i, i2, i3), d2)), ecliptic_longitude(L_sun(t_century(days(i, i2, i3), d2)), G_sun(t_century(days(i, i2, i3), d2))))));
        result_rise(dUT_rise, d2, d4, d5, i, i2, i3);
        return dUT_rise;
    }

    public static double result_set(double d2, double d3, double d4, double d5, int i, int i2, int i3) {
        if ((d2 >= d3 ? d2 - d3 : d3 - d2) < 0.1d) {
            return d2;
        }
        double dUT_set = UT_set(d2, GHA(d2, G_sun(t_century(days(i, i2, i3), d2)), ecliptic_longitude(L_sun(t_century(days(i, i2, i3), d2)), G_sun(t_century(days(i, i2, i3), d2)))), d4, e(f4694h, d5, sun_deviation(earth_tilt(t_century(days(i, i2, i3), d2)), ecliptic_longitude(L_sun(t_century(days(i, i2, i3), d2)), G_sun(t_century(days(i, i2, i3), d2))))));
        result_set(dUT_set, d2, d4, d5, i, i2, i3);
        return dUT_set;
    }

    public static String getSunrise(BigDecimal bigDecimal, BigDecimal bigDecimal2, Date date, TimeZone timeZone) {
        if (date == null || bigDecimal == null || bigDecimal2 == null) {
            return null;
        }
        String[] strArrSplit = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD).format(date).split("-");
        String str = strArrSplit[0];
        String strReplaceAll = strArrSplit[1];
        String str2 = strArrSplit[2];
        int i = Integer.parseInt(str);
        if (strReplaceAll != null && strReplaceAll != "" && strReplaceAll.indexOf(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE) == -1) {
            strReplaceAll = strReplaceAll.replaceAll(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, "");
        }
        int i2 = Integer.parseInt(strReplaceAll);
        int i3 = Integer.parseInt(str2);
        double dDoubleValue = bigDecimal.doubleValue();
        double dDoubleValue2 = bigDecimal2.doubleValue();
        double dResult_rise = result_rise(UT_rise(UTo, GHA(UTo, G_sun(t_century(days(i, i2, i3), UTo)), ecliptic_longitude(L_sun(t_century(days(i, i2, i3), UTo)), G_sun(t_century(days(i, i2, i3), UTo)))), dDoubleValue, e(f4694h, dDoubleValue2, sun_deviation(earth_tilt(t_century(days(i, i2, i3), UTo)), ecliptic_longitude(L_sun(t_century(days(i, i2, i3), UTo)), G_sun(t_century(days(i, i2, i3), UTo)))))), UTo, dDoubleValue, dDoubleValue2, i, i2, i3);
        StringBuilder sb = new StringBuilder();
        double d2 = dResult_rise / 15.0d;
        sb.append(formatTime((int) (((double) Zone(timeZone)) + d2)));
        sb.append(":");
        sb.append(formatTime((int) (((((double) Zone(timeZone)) + d2) - ((double) ((int) (d2 + ((double) Zone(timeZone)))))) * 60.0d)));
        return sb.toString();
    }

    public static String getSunset(BigDecimal bigDecimal, BigDecimal bigDecimal2, Date date, TimeZone timeZone) {
        if (date == null || bigDecimal2 == null || bigDecimal == null) {
            return null;
        }
        String[] strArrSplit = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD).format(date).split("-");
        String str = strArrSplit[0];
        String strReplaceAll = strArrSplit[1];
        String str2 = strArrSplit[2];
        int i = Integer.parseInt(str);
        if (strReplaceAll != null && strReplaceAll != "" && strReplaceAll.indexOf(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE) == -1) {
            strReplaceAll = strReplaceAll.replaceAll(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, "");
        }
        int i2 = Integer.parseInt(strReplaceAll);
        int i3 = Integer.parseInt(str2);
        double dDoubleValue = bigDecimal.doubleValue();
        double dDoubleValue2 = bigDecimal2.doubleValue();
        double dResult_set = result_set(UT_set(UTo, GHA(UTo, G_sun(t_century(days(i, i2, i3), UTo)), ecliptic_longitude(L_sun(t_century(days(i, i2, i3), UTo)), G_sun(t_century(days(i, i2, i3), UTo)))), dDoubleValue, e(f4694h, dDoubleValue2, sun_deviation(earth_tilt(t_century(days(i, i2, i3), UTo)), ecliptic_longitude(L_sun(t_century(days(i, i2, i3), UTo)), G_sun(t_century(days(i, i2, i3), UTo)))))), UTo, dDoubleValue, dDoubleValue2, i, i2, i3);
        CommonLogUtil.printAndSave("getSunset: longitude  =  " + bigDecimal + ", latitude = " + bigDecimal2 + ", sunTime = " + date + ", zone = " + timeZone + ", sunset = " + dResult_set);
        StringBuilder sb = new StringBuilder();
        double d2 = dResult_set / 15.0d;
        sb.append(formatTime((int) (((double) Zone(timeZone)) + d2)));
        sb.append(":");
        sb.append(formatTime((int) (((((double) Zone(timeZone)) + d2) - ((double) ((int) (d2 + ((double) Zone(timeZone)))))) * 60.0d)));
        return sb.toString();
    }

    private static String formatTime(int i) {
        CommonLogUtil.printAndSave("formatTime: " + i);
        return new DecimalFormat("00").format(i);
    }

    private static int Zone(TimeZone timeZone) {
        int rawOffset = (timeZone.getRawOffset() + (timeZone.inDaylightTime(new Date()) ? timeZone.getDSTSavings() : 0)) / 3600000;
        CommonLogUtil.printAndSave("Zone: " + rawOffset + ", local = " + timeZone);
        return rawOffset;
    }
}