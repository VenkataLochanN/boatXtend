package com.ido.life.util;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.google.android.material.timepicker.TimeModel;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NumUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes3.dex */
public class DateUtil {
    public static final String DATE_FORMAT_DM = "d/M";
    public static final String DATE_FORMAT_DMY = "dd-MM-yyyy";
    public static final String DATE_FORMAT_DMYHm = "dd/MM/yyyy HH:mm";
    public static final String DATE_FORMAT_DMYHm_1 = "dd-MM-yyyy HH:mm";
    public static final String DATE_FORMAT_DMYHms = "dd-MM-yyyy HH:mm:ss";
    public static final String DATE_FORMAT_DMYHms_1 = "dd-MM-yyyy_HH:mm:ss";
    public static final String DATE_FORMAT_DMY_1 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DM_1 = "dd/MM";
    public static final String DATE_FORMAT_DM_2 = "dd-MM";
    public static final String DATE_FORMAT_Hm = "HH:mm";
    public static final String DATE_FORMAT_MD = "M/d";
    public static final String DATE_FORMAT_MDHm = "MM/dd HH:mm";
    public static final String DATE_FORMAT_MD_2 = "MM/dd";
    public static final String DATE_FORMAT_MY = "MM/yyyy";
    public static final String DATE_FORMAT_MY_1 = "M/yyyy";
    public static final String DATE_FORMAT_YM = "yyyy/M";
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YMDHm = "yyyy/MM/dd HH:mm";
    public static final String DATE_FORMAT_YMDHms = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YMDHms_2 = "yyyy-MM-dd_HH:mm:ss";
    public static final String DATE_FORMAT_YMDHmssss = "yyyy-MM-dd HH:mm:ss.sss";
    public static final String DATE_FORMAT_YMD_2 = "yyyy/MM/dd";
    public static final String DATE_FORMAT_YM_2 = "yyyy/MM";
    public static final String DATE_FORMAT_YM_3 = "yyyy-MM";
    public static final long DAY = 86400000;
    public static final long HOUR = 3600000;
    public static final long MINUTE = 60000;
    public static final int UNIT_DAY = 0;
    public static final int UNIT_HOER = 1;
    public static final int UNIT_MINUTE = 2;
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    public static final String DATE_FORMAT_MDs = "MM-dd";
    public static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(DATE_FORMAT_MDs, Locale.CHINA);

    public static Date parse(String str, String str2) {
        try {
            return new SimpleDateFormat(str2, Locale.getDefault()).parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getMonthDay(Date date) {
        return new SimpleDateFormat(DATE_FORMAT_MDs, Locale.getDefault()).format(date);
    }

    public static String getWeekDayIndex(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = DATE_FORMAT_YMD;
        }
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(str2);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat3.parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return String.valueOf(calendar.get(7));
    }

    public static String getMonth(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = R.string.menstrual_month_jan;
                break;
            case 2:
                i2 = R.string.menstrual_month_feb;
                break;
            case 3:
                i2 = R.string.menstrual_month_mar;
                break;
            case 4:
                i2 = R.string.menstrual_month_apr;
                break;
            case 5:
                i2 = R.string.menstrual_month_may;
                break;
            case 6:
                i2 = R.string.menstrual_month_jun;
                break;
            case 7:
                i2 = R.string.menstrual_month_jul;
                break;
            case 8:
                i2 = R.string.menstrual_month_aug;
                break;
            case 9:
                i2 = R.string.menstrual_month_sep;
                break;
            case 10:
                i2 = R.string.menstrual_month_oct;
                break;
            case 11:
                i2 = R.string.menstrual_month_nov;
                break;
            case 12:
                i2 = R.string.menstrual_month_dec;
                break;
            default:
                i2 = -1;
                break;
        }
        return i2 > 0 ? LanguageUtil.getLanguageText(i2) : "";
    }

    public static String getWeek(int i) {
        switch (i) {
            case 1:
                return LanguageUtil.getLanguageText(R.string.device_week_sun_android);
            case 2:
                return LanguageUtil.getLanguageText(R.string.device_week_mon_android);
            case 3:
                return LanguageUtil.getLanguageText(R.string.device_week_tue_android);
            case 4:
                return LanguageUtil.getLanguageText(R.string.device_week_wed_android);
            case 5:
                return LanguageUtil.getLanguageText(R.string.device_week_thu_android);
            case 6:
                return LanguageUtil.getLanguageText(R.string.device_week_fri_android);
            case 7:
                return LanguageUtil.getLanguageText(R.string.device_week_sat_android);
            default:
                return "";
        }
    }

    public static long getSportStartTime(int i, int i2, int i3) {
        String str = new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(new Date());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i)));
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)));
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3)));
        return Long.parseLong(stringBuffer.toString());
    }

    public static Date getDate(int i, int i2, int i3) {
        return new Date(i - 1900, i2 - 1, i3, 0, 0, 0);
    }

    public static String getMouth(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(str2, Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat3.parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        if (date == null) {
            return "";
        }
        int month = date.getMonth() + 1;
        if (month < 10) {
            return AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + month;
        }
        return String.valueOf(month);
    }

    public static String getDay(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(str2, Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat3.parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        if (date == null) {
            return "";
        }
        int day = date.getDay();
        if (day < 10) {
            return AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + day;
        }
        return String.valueOf(day);
    }

    public static int getCurrentDay() {
        return Calendar.getInstance().get(5);
    }

    public static int getCurrentHour() {
        return Calendar.getInstance().get(11);
    }

    public static int getCurrentMin() {
        return Calendar.getInstance().get(12);
    }

    public static int getCurrentSecond() {
        return Calendar.getInstance().get(13);
    }

    public static String computeTime(long j) {
        int i = (int) ((j / 60) / 60);
        StringBuffer stringBuffer = new StringBuffer();
        if (i > 0) {
            if (i < 10) {
                stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            }
            j -= (long) ((i * 60) * 60);
            stringBuffer.append(i + ":");
        }
        int i2 = (int) (j / 60);
        if (i2 < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        }
        int i3 = (int) (j - ((long) (i2 * 60)));
        stringBuffer.append(i2 + ":");
        if (i3 < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        }
        stringBuffer.append(i3);
        return stringBuffer.toString();
    }

    public static String computeTimeMS(int i) {
        return String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i / 60)) + "'" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i % 60)) + "''";
    }

    public static String computeTimePace(String str) {
        if (str == null) {
            str = "";
        }
        if (str.contains(".")) {
            String[] strArrSplit = str.split("\\.");
            return String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(Integer.parseInt(strArrSplit[0]))) + "'" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((Integer.parseInt(strArrSplit[1]) * 60) / 100)) + "''";
        }
        if (str.contains(AppInfo.DELIM)) {
            String[] strArrSplit2 = str.split(AppInfo.DELIM);
            return String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(Integer.parseInt(strArrSplit2[0]))) + "'" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((Integer.parseInt(strArrSplit2[1]) * 60) / 100)) + "''";
        }
        return String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, 0) + "'" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, 0) + "''";
    }

    public static String computeTimeHMS(long j) {
        int i = (int) ((j / 60) / 60);
        StringBuffer stringBuffer = new StringBuffer();
        long j2 = j - ((long) ((i * 60) * 60));
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i)));
        stringBuffer.append(":");
        int i2 = (int) (j2 / 60);
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)));
        stringBuffer.append(":");
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((int) (j2 - ((long) (i2 * 60))))));
        return stringBuffer.toString();
    }

    public static String computeTimeHM(long j) {
        int i = (int) ((j / 60) / 60);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i)));
        stringBuffer.append(":");
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((int) ((j - ((long) ((i * 60) * 60))) / 60))));
        return stringBuffer.toString();
    }

    public static String computeTimeMS(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((int) ((j - ((long) ((((int) ((j / 60) / 60)) * 60) * 60))) / 60))));
        return stringBuffer.toString();
    }

    public static String format2(int i, int i2, int i3) {
        return i + "/" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)) + "/" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3));
    }

    public static String format3(int i, int i2, int i3) {
        return "" + i + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)) + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3));
    }

    public static String format(int i, int i2, int i3) {
        return i + "-" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)) + "-" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3));
    }

    public static String format(int i, int i2, int i3, int i4, int i5, int i6) {
        return i + "-" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)) + "-" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3)) + " " + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i4)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i5)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i6));
    }

    public static String format(int i) {
        return String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i));
    }

    public static String getFormatDate(String str, Date date) {
        if (date == null) {
            date = new Date();
        }
        if (TextUtils.isEmpty(str)) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(str, Locale.CHINA).format(date);
    }

    public static String getFormatDate(String str, Calendar calendar) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        return getFormatDate(str, calendar.getTime());
    }

    public static String getFormatDate(String str, long j) {
        return getFormatDate(str, new Date(j));
    }

    public static String getFormatDate(String str, int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.set(5, i3);
        return getFormatDate(str, calendar.getTime());
    }

    public static String getFormatDate(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.set(5, i3);
        calendar.set(11, i4);
        calendar.set(12, i5);
        calendar.set(13, i6);
        return getFormatDate(str, calendar.getTime());
    }

    public static String formatAdjustDate(SimpleDateFormat simpleDateFormat3, Date date) {
        Date date2 = (Date) date.clone();
        date2.setYear(date.getYear() - 1900);
        return simpleDateFormat3.format(date2);
    }

    public static Date getTodayDate() {
        return Calendar.getInstance().getTime();
    }

    public static boolean isToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int[] currentDate = getCurrentDate();
        return currentDate[0] == calendar.get(1) && currentDate[1] == calendar.get(2) + 1 && currentDate[2] == calendar.get(5);
    }

    public static boolean isToday(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int[] currentDate = getCurrentDate();
        return currentDate[0] == calendar.get(1) && currentDate[1] == calendar.get(2) + 1 && currentDate[2] == calendar.get(5);
    }

    public static int[] todayYearMonthDay() {
        Calendar calendar = Calendar.getInstance();
        return new int[]{calendar.get(1), calendar.get(2) + 1, calendar.get(5)};
    }

    public static int getDataDayHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(11);
    }

    public static int getDataDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5);
    }

    public static int getDataWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(7);
    }

    public static int getDataMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2);
    }

    public static int getTodayHour() {
        return Calendar.getInstance().get(11);
    }

    public static int getTodayMin() {
        return Calendar.getInstance().get(12);
    }

    public static int[] yearMonthDay(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getFormatDate(DATE_FORMAT_YMD, getTodayDate());
        }
        String[] strArrSplit = str.split("-");
        int[] iArr = new int[3];
        for (int i = 0; i < strArrSplit.length && i < iArr.length; i++) {
            iArr[i] = NumUtil.parseInt(strArrSplit[i]).intValue();
        }
        return iArr;
    }

    public static String getUpLoadServiceDate(int i, int i2, int i3) {
        return String.format(Locale.CHINA, "%04d", Integer.valueOf(i)) + "-" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)) + "-" + String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3)) + " 00:00:00";
    }

    public static String getDay() {
        Calendar calendar = Calendar.getInstance();
        StringBuffer stringBuffer = new StringBuffer();
        int i = calendar.get(1);
        if (i < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i);
        } else {
            stringBuffer.append(i);
        }
        stringBuffer.append("-");
        int i2 = calendar.get(2) + 1;
        if (i2 < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i2);
        } else {
            stringBuffer.append(i2);
        }
        stringBuffer.append("-");
        int i3 = calendar.get(5);
        if (i3 < 10) {
            stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + i3);
        } else {
            stringBuffer.append(i3);
        }
        return new String(stringBuffer);
    }

    public static long getLongFromDateStr(String str) {
        return getLongFromDateStr(str, "yyyy-MM-dd HH:mm:ss");
    }

    public static long getLongFromDateStr(String str, String str2) {
        try {
            return new SimpleDateFormat(str2, Locale.CHINA).parse(str).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static Date getLongFromDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return new Date();
        }
    }

    public static Date getDateByHMS(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static int daysBetween(Date date, Date date2) {
        long timeInMillis;
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.CHINA);
        try {
            Date date3 = simpleDateFormat3.parse(simpleDateFormat3.format(date));
            Date date4 = simpleDateFormat3.parse(simpleDateFormat3.format(date2));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date3);
            long timeInMillis2 = calendar.getTimeInMillis();
            calendar.setTime(date4);
            timeInMillis = (calendar.getTimeInMillis() - timeInMillis2) / DAY;
        } catch (ParseException e2) {
            e2.printStackTrace();
            timeInMillis = 0;
        }
        return Integer.parseInt(String.valueOf(timeInMillis));
    }

    public static int daysBetween(String str, String str2) {
        if (TextUtils.equals(str, str2) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.CHINA);
        long timeInMillis = 0;
        try {
            Date date = simpleDateFormat3.parse(str);
            Date date2 = simpleDateFormat3.parse(str2);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            long timeInMillis2 = calendar.getTimeInMillis();
            calendar.setTime(date2);
            timeInMillis = (calendar.getTimeInMillis() - timeInMillis2) / DAY;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return (int) timeInMillis;
    }

    public static String getSpecifiedDayBefore(String str, String str2, int i) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(str2, Locale.CHINA).parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - i);
        return new SimpleDateFormat(str2, Locale.CHINA).format(calendar.getTime());
    }

    public static boolean isBeforeCurrent(Date date) {
        return date.before(Calendar.getInstance().getTime());
    }

    public static Date string2Date(String str, String str2) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(str2, Locale.CHINA);
        if (TextUtils.isEmpty(str)) {
            return date;
        }
        try {
            return simpleDateFormat3.parse(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return date;
        }
    }

    public static int[] getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return new int[]{calendar.get(1), calendar.get(2) + 1, calendar.get(5)};
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(1);
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static int getCurrentMonthNew() {
        return Calendar.getInstance().get(2);
    }

    public static int[] getYearMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new int[]{calendar.get(1), calendar.get(2) + 1, calendar.get(5)};
    }

    public static String getCurrentMonthAndDay() {
        Calendar calendar = Calendar.getInstance();
        return NumUtil.numberFormat(calendar.get(2) + 1) + "/" + NumUtil.numberFormat(calendar.get(5));
    }

    public static String dateFormat(int i, int i2, int i3) {
        return i + "/" + NumUtil.numberFormat(i2) + "/" + NumUtil.numberFormat(i3);
    }

    public static long getTimeInterval(String str, int i, String str2) {
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        try {
            calendar.setTime(new SimpleDateFormat(str2, Locale.CHINA).parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        long timeInMillis2 = calendar.getTimeInMillis() - timeInMillis;
        while (timeInMillis2 <= 0) {
            str = getSpecifiedDayBefore(str, str2, -i);
            getTimeInterval(str, i, str2);
        }
        return timeInMillis2;
    }

    public static String getDateFormatMd(String str, String str2) {
        String[] strArrSplit = str.split(" ")[0].split(str2);
        if (strArrSplit.length < 3) {
            return str;
        }
        return strArrSplit[1] + str2 + strArrSplit[2];
    }

    public static int[] getIntervalTypeAndValue(long j) {
        int[] iArr = new int[2];
        long j2 = j / DAY;
        if (j2 > 0) {
            iArr[0] = 0;
            iArr[1] = (int) j2;
        } else {
            long j3 = j / HOUR;
            if (j3 > 0) {
                iArr[0] = 1;
                iArr[1] = (int) j3;
            } else {
                iArr[0] = 2;
                iArr[1] = (int) (j / MINUTE);
            }
        }
        return iArr;
    }

    public static Date getMondayOfThisWeek() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(7) - 1;
        calendar.add(5, 1 - (i != 0 ? i : 7));
        CommonLogUtil.d("getMondayOfThisWeek = " + calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5));
        return calendar.getTime();
    }

    public static Date getMondayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(7) - 1;
        calendar.add(5, 1 - (i != 0 ? i : 7));
        CommonLogUtil.d("getMondayOfWeek = " + calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5));
        return calendar.getTime();
    }

    public static Date getSundayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, 1 - calendar.get(7));
        CommonLogUtil.d("getSundayOfWeek = " + calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5));
        return calendar.getTime();
    }

    public static Date getSaturdayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(7);
        if (i == 7) {
            i = 0;
        }
        calendar.add(5, -i);
        CommonLogUtil.d("getSaturdayOfLastWeek = " + calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5));
        return calendar.getTime();
    }

    public static Date getSundayOfNextWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(7) - 1;
        if (i == 0) {
            i = 7;
        }
        calendar.add(5, 7 - i);
        CommonLogUtil.d("getSundayOfNextWeek = " + calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5));
        return calendar.getTime();
    }

    public static Date getMondayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, (-(calendar.get(7) - 1 != 0 ? r1 : 7)) - 6);
        CommonLogUtil.d("getMondayOfLastWeek = " + calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5));
        return calendar.getTime();
    }

    public static Date getMondayOfNextWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(7) - 1;
        calendar.add(5, 8 - (i != 0 ? i : 7));
        CommonLogUtil.d("getMondayOfNextWeek = " + calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5));
        return calendar.getTime();
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getFirstDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, -1);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date getFirstDayOfNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 1);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date getLastDayOfYearMonth(Date date) {
        Date todayDate = getTodayDate();
        if (!date.before(todayDate)) {
            return todayDate;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static String getFirstMonthStr(int i) {
        return "01/" + i;
    }

    public static String getLastMonthStr(int i) {
        return NumUtil.numberFormat(12) + "/" + i;
    }

    public static Date getFirstDayOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static String format(Date date, String str) {
        return new SimpleDateFormat(str, Locale.CHINA).format(date);
    }

    public static String format(Calendar calendar, String str) {
        return format(calendar.getTime(), str);
    }

    public static String format(long j, String str) {
        return format(new Date(j), str);
    }

    public static Date getSpecifiedDayBefore(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -i);
        return calendar.getTime();
    }

    public static int getDaysOfMonth(Date date) {
        Date lastDayOfMonth = getLastDayOfMonth(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDayOfMonth);
        return calendar.get(5);
    }

    public static Date getDateOfMonthBySpecifiedDay(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, i);
        return calendar.getTime();
    }

    public static boolean isExpire(Date date) {
        if (date == null) {
            return false;
        }
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(DATE_FORMAT_YMDHm, Locale.CHINA);
        try {
            return !simpleDateFormat3.parse(simpleDateFormat3.format(new Date())).before(date);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static List<String> getDates(String str, String str2) {
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(DATE_FORMAT_YMD, Locale.CHINA);
        ArrayList arrayList = new ArrayList();
        try {
            Date date = simpleDateFormat3.parse(str2);
            Calendar calendar = Calendar.getInstance();
            for (Date time = simpleDateFormat3.parse(str); time.getTime() <= date.getTime(); time = calendar.getTime()) {
                arrayList.add(simpleDateFormat3.format(time));
                calendar.setTime(time);
                calendar.add(5, 1);
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static List<String> getDateListByMonth(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.set(5, 1);
        int actualMaximum = calendar.getActualMaximum(5);
        for (int i3 = 0; i3 < actualMaximum; i3++) {
            arrayList.add(format(calendar.getTime(), DATE_FORMAT_YMD));
            calendar.add(5, 1);
        }
        return arrayList;
    }

    public static int getAgeByBirthday(String str, String str2) {
        Date dateString2Date = string2Date(str, str2);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        if (calendar.before(dateString2Date)) {
            throw new IllegalArgumentException("The birthday is before Now,It's unbelievable");
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(dateString2Date);
        int i4 = calendar2.get(1);
        int i5 = calendar2.get(2);
        int i6 = i - i4;
        return (i2 < i5 || (i2 == i5 && i3 < calendar2.get(5))) ? i6 - 1 : i6;
    }

    public static String computeTimeHMPoint(long j) {
        int i = (int) ((j / 60) / 60);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i)));
        stringBuffer.append(".");
        stringBuffer.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((int) ((j - ((long) ((i * 60) * 60))) / 60))));
        return stringBuffer.toString();
    }

    public static String computeTimeHDMPoint(long j) {
        int i = (int) ((j / 60) / 60);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.NUMBER_FORMAT, Integer.valueOf(i)));
        stringBuffer.append(".");
        stringBuffer.append(String.format(Locale.CHINA, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((int) ((j - ((long) ((i * 60) * 60))) / 60))));
        return stringBuffer.toString();
    }

    public static Date getTimeAfterSeconds(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(13, i);
        return calendar.getTime();
    }

    public static Date formatTimeMills2Date(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.getTime();
    }

    public static int[] getCurrentHM() {
        Calendar calendar = Calendar.getInstance();
        return new int[]{calendar.get(11), calendar.get(12)};
    }

    public static Date getLastDateOfYear(int i) {
        if (getCurrentDate()[0] == i) {
            return getTodayDate();
        }
        return getDate(i, 12, 31);
    }

    public static Date getLongFromDate2(String str) {
        try {
            return new SimpleDateFormat(DATE_FORMAT_YMD, Locale.CHINA).parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return new Date();
        }
    }

    public static int getDateHour(String str, String str2) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat(str2, Locale.CHINA).parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(11);
    }

    public static Date utcToLocalDate(String str, String str2) {
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(str2);
        simpleDateFormat3.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date = simpleDateFormat3.parse(str);
            simpleDateFormat3.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat3.parse(simpleDateFormat3.format(date));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int getDateMinute(String str, String str2) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat(str2, Locale.CHINA).parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(12);
    }

    public static Date localToUTC(String str, String str2) {
        Date date;
        try {
            date = new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            date = null;
        }
        long time = date.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.add(14, -(calendar.get(15) + calendar.get(16)));
        return new Date(calendar.getTimeInMillis());
    }

    public static int byTimeMillisGetYear(Long l) {
        return Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date(l.longValue()))).intValue();
    }

    public static int byTimeMillisGetMonth(Long l) {
        return Integer.valueOf(new SimpleDateFormat(DATE_FORMAT_YMD).format(new Date(l.longValue())).substring(5, 7)).intValue();
    }

    public static int byTimeMillisGetDay(Long l) {
        return Integer.valueOf(new SimpleDateFormat("dd").format(new Date(l.longValue()))).intValue();
    }

    public static String getReversalTime(String str) {
        if (AppUtil.needReversal() == 0) {
            return str;
        }
        String str2 = "";
        String[] strArrSplit = str.split("");
        int length = strArrSplit.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str3 = strArrSplit[i];
            if (TextUtils.isDigitsOnly(str3)) {
                i++;
            } else if ("/".equals(str3) || "-".equals(str3)) {
                str2 = str3;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (AppUtil.needReversal() == 1) {
            return getMdyDate(str, str2);
        }
        return AppUtil.needReversal() == 2 ? getDmyDate(str, str2) : str;
    }

    private static String getMdyDate(String str, String str2) {
        StringBuilder sb = new StringBuilder("");
        try {
            if (str.contains(" ") && str.contains(":")) {
                String[] strArrSplit = str.split(" ");
                String[] strArrSplit2 = strArrSplit[0].split(str2);
                sb.append(strArrSplit2[1]);
                sb.append(str2);
                sb.append(strArrSplit2[2]);
                sb.append(str2);
                sb.append(strArrSplit2[0]);
                sb.append(" ");
                sb.append(strArrSplit[1]);
            } else if (!str.contains(" ") && !str.contains(":")) {
                String[] strArrSplit3 = str.split(str2);
                if (strArrSplit3.length == 3) {
                    sb.append(strArrSplit3[1]);
                    sb.append(str2);
                    sb.append(strArrSplit3[2]);
                    sb.append(str2);
                    sb.append(strArrSplit3[0]);
                } else {
                    if (strArrSplit3.length != 2 || strArrSplit3[0].length() != 4) {
                        return str;
                    }
                    sb.append(strArrSplit3[1]);
                    sb.append(str2);
                    sb.append(strArrSplit3[0]);
                }
            }
            return TextUtils.isEmpty(sb) ? str : sb.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.i("xiaobing", "时间倒序至MM/dd/yyyy HH:ss转换异常-->" + e2.getMessage());
            return str;
        }
    }

    private static String getDmyDate(String str, String str2) {
        StringBuilder sb = new StringBuilder("");
        try {
            if (str.contains(" ") && str.contains(":")) {
                String[] strArrSplit = str.split(" ");
                String[] strArrSplit2 = strArrSplit[0].split(str2);
                for (int length = strArrSplit2.length - 1; length >= 0; length--) {
                    if (length != 0) {
                        sb.append(strArrSplit2[length]);
                        sb.append(str2);
                    } else {
                        sb.append(strArrSplit2[length]);
                    }
                }
                sb.append(" ");
                sb.append(strArrSplit[1]);
            } else if (!str.contains(" ") && !str.contains(":")) {
                String[] strArrSplit3 = str.split(str2);
                for (int length2 = strArrSplit3.length - 1; length2 >= 0; length2--) {
                    if (length2 != 0) {
                        sb.append(strArrSplit3[length2]);
                        sb.append(str2);
                    } else {
                        sb.append(strArrSplit3[length2]);
                    }
                }
            }
            return TextUtils.isEmpty(sb) ? str : sb.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.i("xiaobing", "时间倒序至dd/MM/yyyy HH:ss转换异常-->" + e2.getMessage());
            return str;
        }
    }
}