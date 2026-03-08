package com.ido.common.utils;

import android.text.TextUtils;
import com.google.android.material.timepicker.TimeModel;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.util.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class TimeUtil {
    static final long MILSEC_OND_DAY = 86400000;

    public static String convTimeDetail(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public static String[] splitDateToTime(Calendar calendar) {
        return new String[]{String.valueOf(calendar.get(1)), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(2) + 1)), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(5))), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(11))), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(12))), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(13)))};
    }

    public static String convTimeToDetail(long j) {
        return new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date(j));
    }

    public static String convTimeYMDHM(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(j));
    }

    public static String convTimeHM(long j) {
        return new SimpleDateFormat(DateUtil.DATE_FORMAT_Hm).format(new Date(j));
    }

    public static String convTimeHourMin(long j) {
        String str;
        if (j <= 0) {
            return "00:00";
        }
        int i = (int) (j / DateUtil.HOUR);
        int i2 = (int) ((j / DateUtil.MINUTE) % 60);
        String str2 = "00";
        if (i <= 0) {
            str = "00";
        } else if (i < 10) {
            str = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i;
        } else {
            str = "" + i;
        }
        if (i2 > 0) {
            if (i2 < 10) {
                str2 = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i2;
            } else {
                str2 = "" + i2;
            }
        }
        return String.format("%s:%s", str, str2);
    }

    public static String convTime(long j) {
        return new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD).format(new Date(j));
    }

    public static String getMonth(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return date != null ? splitDate(date)[1] : "";
    }

    public static String getDay(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return date != null ? splitDate(date)[2] : "";
    }

    public static String[] splitDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new String[]{String.valueOf(calendar.get(1)), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(2) + 1)), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(5)))};
    }

    public static String[] splitDate(Calendar calendar) {
        return new String[]{String.valueOf(calendar.get(1)), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(2) + 1)), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(5)))};
    }

    public static String convTimeYMD(long j) {
        return new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD).format(new Date(j));
    }

    public static long convTimeYmdhmsToLong(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static String convTimeEx(long j) {
        if (j <= 0) {
            return "刚刚";
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        int i = (int) (jCurrentTimeMillis / DateUtil.MINUTE);
        int i2 = (int) (jCurrentTimeMillis / DateUtil.HOUR);
        int i3 = (int) (jCurrentTimeMillis / 86400000);
        if (i < 0) {
            return convTimeYMD(j);
        }
        return i < 5 ? "刚刚" : i < 60 ? String.format("%d分钟前", Integer.valueOf(i)) : i2 < 24 ? String.format("%d小时前", Integer.valueOf(i2)) : i3 < 7 ? String.format("%d天前", Integer.valueOf(i3)) : convTimeYMD(j);
    }

    public static String convTimeForChatIndex(long j) {
        long jCurrentTimeMillis = (System.currentTimeMillis() / 86400000) - (j / 86400000);
        if (jCurrentTimeMillis > 7) {
            return convTimeYMD(j);
        }
        if (jCurrentTimeMillis <= 1) {
            return jCurrentTimeMillis > 0 ? "昨天 " : convTimeHM(j);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(j);
        String[] strArr = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int i = calendar.get(7) - 1;
        if (i < 0) {
            i = 0;
        }
        return strArr[i % 7];
    }

    public static boolean isTimeYesterday(long j) {
        return System.currentTimeMillis() / 86400000 > j / 86400000;
    }

    public static String getAge(String str) {
        Date date;
        try {
            date = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD).parse(str);
        } catch (Exception unused) {
            date = null;
        }
        Date date2 = new Date(System.currentTimeMillis());
        if (date == null) {
            return "18";
        }
        return (date2.getYear() - date.getYear()) + "";
    }

    public static String getConstellation(String str) {
        String[] strArr = {"魔羯座", "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
        try {
            Date date = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD).parse(str);
            int month = date.getMonth() + 1;
            int date2 = date.getDate();
            if (month < 13 && month > 0 && date2 > 0 && date2 < 32) {
                int i = month - 1;
                if (date2 <= new int[]{20, 18, 20, 20, 20, 21, 22, 22, 22, 22, 21, 21}[i]) {
                    month = i;
                }
                if (month >= 0) {
                    return strArr[month];
                }
                return strArr[11];
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static String getChatTime(long j) {
        long jCurrentTimeMillis = (System.currentTimeMillis() / 86400000) - (j / 86400000);
        if (jCurrentTimeMillis > 7) {
            return convTimeYMDHM(j);
        }
        if (jCurrentTimeMillis <= 1) {
            if (jCurrentTimeMillis > 0) {
                return "昨天 " + convTimeHM(j);
            }
            return convTimeHM(j);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTimeInMillis(j);
        String[] strArr = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int i = calendar.get(7) - 1;
        if (i < 0) {
            i = 0;
        }
        return strArr[i % 7] + " " + convTimeHM(j);
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static String getCurrentDayDate() {
        return new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD).format(new Date());
    }

    public static String getCurrentYearDate() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }

    public static String convTimeForPraise(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i = (int) ((jCurrentTimeMillis / 1000) - (j / 1000));
        if (i < 1) {
            return "刚刚";
        }
        if (i >= 1 && i < 60) {
            return String.format("%d秒前", Integer.valueOf(i));
        }
        int i2 = (int) ((jCurrentTimeMillis / DateUtil.MINUTE) - (j / DateUtil.MINUTE));
        if (i2 >= 1 && i2 < 60) {
            return String.format("%d分钟前", Integer.valueOf(i2));
        }
        int i3 = (int) ((jCurrentTimeMillis / DateUtil.HOUR) - (j / DateUtil.HOUR));
        if (i3 >= 1 && i3 < 24) {
            return String.format("%d小时前", Integer.valueOf(i3));
        }
        int iDaysBetween = daysBetween(new Date(j), new Date(jCurrentTimeMillis));
        return (iDaysBetween < 1 || iDaysBetween >= 7) ? convTimeYMD(j) : String.format("%d天前", Integer.valueOf(iDaysBetween));
    }

    public static int daysBetween(Date date, Date date2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
            date2 = simpleDateFormat.parse(simpleDateFormat.format(date2));
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.setTime(date2);
        return Integer.parseInt(String.valueOf((calendar.getTimeInMillis() - timeInMillis) / 86400000));
    }

    public static long now() {
        return System.currentTimeMillis();
    }

    public static long zero() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime().getTime();
    }

    public static long twentyFour() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(5, 1);
        calendar.add(13, -1);
        return calendar.getTime().getTime();
    }

    public static long getTodayHours() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        return (jCurrentTimeMillis - ((28800 + jCurrentTimeMillis) % 86400)) * 1000;
    }

    public static String getChronometerFormat(long j) {
        long j2 = j / 1000;
        long j3 = j2 / 60;
        int i = (int) (j3 / 60);
        int i2 = ((int) j3) % 60;
        int i3 = ((int) j2) % 60;
        if (i >= 1) {
            return i < 10 ? "0%s" : "%s";
        }
        if (i2 == 59 && i3 == 59) {
            return "0%s";
        }
        return AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i + ":%s";
    }
}