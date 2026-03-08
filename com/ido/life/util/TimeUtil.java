package com.ido.life.util;

import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import com.boat.Xtend.two.R;
import com.google.android.material.timepicker.TimeModel;
import com.ido.common.IdoApp;
import com.ido.common.constant.LanguageRegion;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.StringUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class TimeUtil {
    private static ThreadLocal<Long> nanTime = new ThreadLocal<>();

    public static int format12To24(int i, boolean z) {
        if (z) {
            return i;
        }
        if (i == 12) {
            return 0;
        }
        return i + 12;
    }

    public static int getDayMonthOfCurrentYear() {
        return 12;
    }

    public static int getDayOfCurrentWeek() {
        return 7;
    }

    public static int getMonthOfYearMonthDay(int i, int i2, int i3, int i4) {
        return i > i3 ? ((i - i3) * 12) + (i2 - i4) : (((i3 - i) * 12) + i4) - i2;
    }

    public static boolean isAM(int i) {
        return i < 12;
    }

    public static String formatTime(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String formatTimeNoS(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }

    public static String formatTime(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new SimpleDateFormat(DateUtil.DATE_FORMAT_YMDHm).format(simpleDateFormat.parse(str));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(2);
    }

    public static String getDayHistoryMonth(String str, int i) {
        return getFirstDayOfMonth(str, i) + "-" + getLastDayOfMonth(str, i);
    }

    public static String getFirstDayOfMonth(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, i);
        calendar.set(5, calendar.getMinimum(5));
        return new SimpleDateFormat(str).format(calendar.getTime());
    }

    public static String getLastDayOfMonth(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, i);
        calendar.set(5, calendar.getActualMaximum(5));
        return new SimpleDateFormat(str).format(calendar.getTime());
    }

    public static int[] getCurrentYearMonthDay() {
        Calendar calendar = Calendar.getInstance();
        return new int[]{calendar.get(1), calendar.get(2) + 1, calendar.get(5)};
    }

    public static int getDaysByYearMonth(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2 - 1);
        calendar.set(5, 1);
        calendar.roll(5, -1);
        return calendar.get(5);
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(1);
    }

    public static boolean is24Hour() {
        return "24".equals(Settings.System.getString(IdoApp.getAppContext().getContentResolver(), "time_12_24"));
    }

    public static String timeStamp2Date(long j, String str) {
        return TextUtils.isEmpty(str) ? "" : new SimpleDateFormat(str).format(new Date(Long.valueOf(j).longValue()));
    }

    public static Date getDateFromString(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            return new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Calendar getCalendarFromString(String str, String str2) {
        Date dateFromString = getDateFromString(str, str2);
        if (dateFromString == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFromString);
        return calendar;
    }

    public static String date2TimeStamp(String str, String str2) {
        try {
            return String.valueOf(new SimpleDateFormat(str2).parse(str).getTime());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getYearMonthDay(int i, int i2, int i3) {
        return "" + i + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2)) + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3));
    }

    public static int getToDayTotalMinutes(long j) {
        String[] strArrSplit = timeStamp2Date(j, "HH/mm").split("/");
        return (Integer.parseInt(strArrSplit[0]) * 60) + Integer.parseInt(strArrSplit[1]);
    }

    public static long getStartDateToStamp(int i, int i2, int i3) {
        return dateToStamp(i, i2, i3, 0, 0, 0);
    }

    public static Date getStartDate(int i, int i2, int i3) {
        return getDate(i, i2, i3, 0, 0, 0);
    }

    public static long getEndDateToStamp(int i, int i2, int i3) {
        return dateToStamp(i, i2, i3, 23, 59, 59);
    }

    public static Date getEndDate(int i, int i2, int i3) {
        return getDate(i, i2, i3, 23, 59, 59);
    }

    public static long dateToStamp(int i, int i2, int i3, int i4, int i5, int i6) {
        Date date = getDate(i, i2, i3, i4, i5, i6);
        if (date != null) {
            return date.getTime();
        }
        return 0L;
    }

    public static int getMonth() {
        return GregorianCalendar.getInstance().get(2) + 1;
    }

    public static int getYear() {
        return GregorianCalendar.getInstance().get(1);
    }

    public static int getDay() {
        return GregorianCalendar.getInstance().get(5);
    }

    public static int getHour() {
        return GregorianCalendar.getInstance().get(11);
    }

    public static int getMinute() {
        return GregorianCalendar.getInstance().get(12);
    }

    public static int getSecond() {
        return GregorianCalendar.getInstance().get(13);
    }

    public static Date getDate() {
        return getDate(getYear(), getMonth(), getDay());
    }

    public static Date getDate(int i, int i2, int i3) {
        return getDate(i, i2, i3, 0, 0, 0);
    }

    public static Date getDate(int i, int i2, int i3, int i4, int i5, int i6) {
        return new GregorianCalendar(i, i2 - 1, i3, i4, i5, i6).getTime();
    }

    public static boolean isSameDay(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date(j2));
        return calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2) && calendar.get(5) == calendar2.get(5);
    }

    public static int getWeekOfYearMonthDay(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, i3);
        calendar.setFirstDayOfWeek(1);
        return calendar.get(3);
    }

    public static String timeFormatter(int i, boolean z, String[] strArr, boolean z2) {
        int i2;
        int i3;
        String str = "";
        if (i < 0 || i >= 1440) {
            if (i < 1440) {
                return "00:00";
            }
            int i4 = i - 1440;
            if (i4 > 0) {
                i2 = getHourAndMin(i4, z)[0];
                i3 = i4 % 60;
            } else {
                i2 = 0;
                i3 = 0;
            }
            if (z) {
                Object[] objArr = new Object[2];
                if (i2 == 24) {
                    i2 = 0;
                }
                objArr[0] = Integer.valueOf(i2);
                objArr[1] = Integer.valueOf(i3);
                return String.format("%1$02d:%2$02d", objArr);
            }
            if (z2) {
                if (strArr != null) {
                    str = i4 < 720 ? strArr[0] : strArr[1];
                } else {
                    str = i4 < 720 ? "am" : "pm";
                }
            }
            StringBuilder sb = new StringBuilder();
            Object[] objArr2 = new Object[2];
            if (i2 == 24) {
                i2 = 0;
            }
            objArr2[0] = Integer.valueOf(i2);
            objArr2[1] = Integer.valueOf(i3);
            sb.append(String.format("%1$02d:%2$02d", objArr2));
            sb.append(str);
            return sb.toString();
        }
        int i5 = getHourAndMin(i, z)[0];
        int i6 = i % 60;
        if (z) {
            Object[] objArr3 = new Object[2];
            if (i5 == 24) {
                i5 = 0;
            }
            objArr3[0] = Integer.valueOf(i5);
            objArr3[1] = Integer.valueOf(i6);
            return String.format("%1$02d:%2$02d", objArr3);
        }
        if (z2) {
            if (strArr != null) {
                str = i < 720 ? strArr[0] : strArr[1];
            } else {
                str = i < 720 ? "am" : "pm";
            }
        }
        if (str.equals("下午") || str.equals("上午")) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            Object[] objArr4 = new Object[2];
            if (i5 == 24) {
                i5 = 0;
            }
            objArr4[0] = Integer.valueOf(i5);
            objArr4[1] = Integer.valueOf(i6);
            sb2.append(String.format("%1$02d:%2$02d", objArr4));
            return sb2.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        Object[] objArr5 = new Object[2];
        if (i5 == 24) {
            i5 = 0;
        }
        objArr5[0] = Integer.valueOf(i5);
        objArr5[1] = Integer.valueOf(i6);
        sb3.append(String.format("%1$02d:%2$02d", objArr5));
        sb3.append(str);
        return sb3.toString();
    }

    public static String timeFormatter(int i, boolean z, String[] strArr) {
        int i2;
        int i3;
        String str;
        String str2;
        if (i >= 0 && i < 1440) {
            int i4 = getHourAndMin(i, z)[0];
            int i5 = i % 60;
            if (z) {
                Object[] objArr = new Object[2];
                if (i4 == 24) {
                    i4 = 0;
                }
                objArr[0] = Integer.valueOf(i4);
                objArr[1] = Integer.valueOf(i5);
                return String.format("%1$02d:%2$02d", objArr);
            }
            if (strArr != null) {
                str2 = i < 720 ? strArr[0] : strArr[1];
            } else {
                str2 = i < 720 ? "am" : "pm";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            Object[] objArr2 = new Object[2];
            if (i4 == 24) {
                i4 = 0;
            }
            objArr2[0] = Integer.valueOf(i4);
            objArr2[1] = Integer.valueOf(i5);
            sb.append(String.format("%1$02d:%2$02d", objArr2));
            return sb.toString();
        }
        if (i < 1440) {
            return "00:00";
        }
        int i6 = i - 1440;
        if (i6 > 0) {
            i2 = getHourAndMin(i6, z)[0];
            i3 = i6 % 60;
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (z) {
            Object[] objArr3 = new Object[2];
            if (i2 == 24) {
                i2 = 0;
            }
            objArr3[0] = Integer.valueOf(i2);
            objArr3[1] = Integer.valueOf(i3);
            return String.format("%1$02d:%2$02d", objArr3);
        }
        if (strArr != null) {
            str = i6 < 720 ? strArr[0] : strArr[1];
        } else {
            str = i6 < 720 ? "am" : "pm";
        }
        StringBuilder sb2 = new StringBuilder();
        Object[] objArr4 = new Object[2];
        if (i2 == 24) {
            i2 = 0;
        }
        objArr4[0] = Integer.valueOf(i2);
        objArr4[1] = Integer.valueOf(i3);
        sb2.append(String.format("%1$02d:%2$02d", objArr4));
        sb2.append(str);
        return sb2.toString();
    }

    public static String timeFormatter(int i, int i2, boolean z, String[] strArr, boolean z2) {
        int i3;
        int i4;
        String str = "";
        if (i >= 0 && i < 1440) {
            int i5 = getHourAndMin(i, z)[0];
            int i6 = i % 60;
            if (z) {
                Object[] objArr = new Object[3];
                if (i5 == 24) {
                    i5 = 0;
                }
                objArr[0] = Integer.valueOf(i5);
                objArr[1] = Integer.valueOf(i6);
                objArr[2] = Integer.valueOf(i2);
                return String.format("%1$02d:%2$02d:%3$02d", objArr);
            }
            if (z2) {
                if (strArr != null) {
                    str = i < 720 ? strArr[0] : strArr[1];
                } else {
                    str = i < 720 ? "am" : "pm";
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            Object[] objArr2 = new Object[3];
            if (i5 == 24) {
                i5 = 0;
            }
            objArr2[0] = Integer.valueOf(i5);
            objArr2[1] = Integer.valueOf(i6);
            objArr2[2] = Integer.valueOf(i2);
            sb.append(String.format("%1$02d:%2$02d:%3$02d", objArr2));
            return sb.toString();
        }
        if (i < 1440) {
            return "00:00";
        }
        int i7 = i - 1440;
        if (i7 > 0) {
            i3 = getHourAndMin(i7, z)[0];
            i4 = i7 % 60;
        } else {
            i3 = 0;
            i4 = 0;
        }
        if (z) {
            Object[] objArr3 = new Object[3];
            if (i3 == 24) {
                i3 = 0;
            }
            objArr3[0] = Integer.valueOf(i3);
            objArr3[1] = Integer.valueOf(i4);
            objArr3[2] = Integer.valueOf(i2);
            return String.format("%1$02d:%2$02d:%3$02d", objArr3);
        }
        if (z2) {
            if (strArr != null) {
                str = i7 < 720 ? strArr[0] : strArr[1];
            } else {
                str = i7 < 720 ? "am" : "pm";
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        Object[] objArr4 = new Object[3];
        if (i3 == 24) {
            i3 = 0;
        }
        objArr4[0] = Integer.valueOf(i3);
        objArr4[1] = Integer.valueOf(i4);
        objArr4[2] = Integer.valueOf(i2);
        sb2.append(String.format("%1$02d:%2$02d:%3$02d", objArr4));
        return sb2.toString();
    }

    public static String timeFormatter(int i, boolean z, String[] strArr, boolean z2, boolean z3) {
        int i2;
        int i3;
        String str = "";
        if (i >= 0 && i < 1440) {
            int i4 = getHourAndMin(i, z)[0];
            int i5 = i % 60;
            if (!z3 && i5 != 0) {
                i4++;
            }
            if (z) {
                Object[] objArr = new Object[2];
                if (i4 == 24) {
                    i4 = 0;
                }
                objArr[0] = Integer.valueOf(i4);
                objArr[1] = 0;
                return String.format("%1$02d:%2$02d", objArr);
            }
            if (z2) {
                if (strArr != null) {
                    str = i < 720 ? strArr[0] : strArr[1];
                } else {
                    str = i < 720 ? "am" : "pm";
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            Object[] objArr2 = new Object[2];
            if (i4 == 24) {
                i4 = 0;
            }
            objArr2[0] = Integer.valueOf(i4);
            objArr2[1] = 0;
            sb.append(String.format("%1$02d:%2$02d", objArr2));
            return sb.toString();
        }
        if (i < 1440) {
            return "00:00";
        }
        int i6 = i - 1440;
        if (i6 > 0) {
            i2 = getHourAndMin(i6, z)[0];
            i3 = i6 % 60;
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (!z3 && i3 != 0) {
            i2++;
        }
        if (z) {
            Object[] objArr3 = new Object[2];
            if (i2 == 24) {
                i2 = 0;
            }
            objArr3[0] = Integer.valueOf(i2);
            objArr3[1] = 0;
            return String.format("%1$02d:%2$02d", objArr3);
        }
        if (z2) {
            if (strArr != null) {
                str = i6 < 720 ? strArr[0] : strArr[1];
            } else {
                str = i6 < 720 ? "am" : "pm";
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        Object[] objArr4 = new Object[2];
        if (i2 == 24) {
            i2 = 0;
        }
        objArr4[0] = Integer.valueOf(i2);
        objArr4[1] = 0;
        sb2.append(String.format("%1$02d:%2$02d", objArr4));
        return sb2.toString();
    }

    public static String timeFormatter(int i, int i2, boolean z, String[] strArr) {
        if (z) {
            Locale locale = Locale.getDefault();
            Object[] objArr = new Object[2];
            if (i == 24) {
                i = 0;
            }
            objArr[0] = Integer.valueOf(i);
            objArr[1] = Integer.valueOf(i2);
            return String.format(locale, "%1$02d:%2$02d", objArr);
        }
        String str = (i == 0 || i > 12) ? strArr[1] : strArr[0];
        if (i > 12) {
            i -= 12;
        }
        if (IdoApp.getAppContext().getResources().getConfiguration().locale.getLanguage().endsWith(LanguageRegion.ZH)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" ");
            Locale locale2 = Locale.getDefault();
            Object[] objArr2 = new Object[2];
            if (i == 0) {
                i = 12;
            }
            objArr2[0] = Integer.valueOf(i);
            objArr2[1] = Integer.valueOf(i2);
            sb.append(String.format(locale2, "%1$02d:%2$02d", objArr2));
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        Locale locale3 = Locale.getDefault();
        Object[] objArr3 = new Object[2];
        if (i == 0) {
            i = 12;
        }
        objArr3[0] = Integer.valueOf(i);
        objArr3[1] = Integer.valueOf(i2);
        sb2.append(String.format(locale3, "%1$02d:%2$02d", objArr3));
        sb2.append(" ");
        sb2.append(str);
        return sb2.toString();
    }

    public static String timeFormatterForTimeAxis(int i, boolean z, String[] strArr, boolean z2) {
        int i2;
        int i3;
        String str = "";
        if (i >= 0 && i < 1440) {
            int i4 = getHourAndMin(i, z)[0];
            int i5 = i % 60;
            if (z) {
                Object[] objArr = new Object[2];
                if (i4 == 24) {
                    i4 = 0;
                }
                objArr[0] = Integer.valueOf(i4);
                objArr[1] = Integer.valueOf(i5);
                return StringUtil.format("%1$02d:%2$02d", objArr);
            }
            if (z2) {
                if (strArr != null) {
                    str = i < 720 ? strArr[0] : strArr[1];
                } else {
                    str = i < 720 ? "am" : "pm";
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            Object[] objArr2 = new Object[2];
            if (i4 == 24) {
                i4 = 0;
            }
            objArr2[0] = Integer.valueOf(i4);
            objArr2[1] = Integer.valueOf(i5);
            sb.append(StringUtil.format("%1$02d:%2$02d", objArr2));
            return sb.toString();
        }
        if (i < 1440) {
            return "00:00";
        }
        int i6 = i - 1440;
        if (i6 > 0) {
            i2 = getHourAndMin(i6, z)[0];
            i3 = i6 % 60;
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (z) {
            Object[] objArr3 = new Object[2];
            if (i2 == 24) {
                i2 = 0;
            }
            objArr3[0] = Integer.valueOf(i2);
            objArr3[1] = Integer.valueOf(i3);
            return StringUtil.format("%1$02d:%2$02d", objArr3);
        }
        if (z2) {
            if (strArr != null) {
                str = i6 < 720 ? strArr[0] : strArr[1];
            } else {
                str = i6 < 720 ? "am" : "pm";
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        Object[] objArr4 = new Object[2];
        if (i2 == 24) {
            i2 = 0;
        }
        objArr4[0] = Integer.valueOf(i2);
        objArr4[1] = Integer.valueOf(i3);
        sb2.append(StringUtil.format("%1$02d:%2$02d", objArr4));
        return sb2.toString();
    }

    public static int[] getHourAndMin(int i, boolean z) {
        int i2 = i / 60;
        if (!z) {
            if (i2 % 12 == 0) {
                i2 = 12;
            } else if (i2 > 12) {
                i2 -= 12;
            }
        }
        return new int[]{i2, i % 60};
    }

    public static String getDayHistoryYear(int i) {
        return i + "/01-" + i + "/12";
    }

    public static int format24To12(int i) {
        int i2 = i % 12;
        if (i == 12 || i2 == 0) {
            return 12;
        }
        return i2;
    }

    public static int formatHourByAm_or_pm(int i, boolean z) {
        if (z) {
            if (i > 0 && i <= 12) {
                return i;
            }
            if (i == 0) {
                return 12;
            }
            return i % 12;
        }
        if (i == 0 || i > 12) {
            return i;
        }
        int i2 = i + 12;
        if (i2 == 24) {
            return 0;
        }
        return i2;
    }

    public static String formatTime(int i, int i2) {
        return String.format("%02d:%02d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static void startTime() {
        CommonLogUtil.d("***********开始计时**********");
        nanTime.set(Long.valueOf(SystemClock.elapsedRealtime()));
    }

    public static long completTime() {
        Long l = nanTime.get();
        long jElapsedRealtime = SystemClock.elapsedRealtime() - (l != null ? l.longValue() : 0L);
        CommonLogUtil.d("***********耗时" + jElapsedRealtime + "ms**********");
        return jElapsedRealtime;
    }

    public static String formatTime(int i, int i2, boolean z) {
        String languageText;
        if (z) {
            return i + ":" + NumUtil.numberFormat(i2);
        }
        if ((i > 0 && i < 12) || i == 24 || i == 0) {
            languageText = LanguageUtil.getLanguageText(R.string.public_am);
        } else {
            languageText = LanguageUtil.getLanguageText(R.string.public_pm);
        }
        return languageText + " " + format24To12(i) + ":" + NumUtil.numberFormat(i2);
    }

    public static long getUTCTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(14, -(calendar.get(15) + calendar.get(16)));
        return calendar.getTimeInMillis() / 1000;
    }

    public static int compareDate(Calendar calendar, Calendar calendar2) {
        int i = 0;
        while (!calendar.after(calendar2)) {
            i++;
            calendar.add(5, 1);
        }
        return (i - 1) / 365;
    }

    public static String getTimeZone() {
        return new SimpleDateFormat("zzz", Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    public static String getCurrentDateBefore(int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -i);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static Pair<String, String> getWeekDateRange() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
        int weekStart = RunTimeUtil.getInstance().getWeekStart();
        CommonLogUtil.d("getWeekDateRange weekStart = " + weekStart);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(weekStart);
        calendar.set(7, weekStart);
        String str = simpleDateFormat.format(calendar.getTime());
        calendar.add(5, 6);
        return new Pair<>(str, simpleDateFormat.format(calendar.getTime()));
    }

    public static Pair<String, String> getLastWeekDateRange() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
        int weekStart = RunTimeUtil.getInstance().getWeekStart();
        CommonLogUtil.d("getLastWeekDateRange weekStart = " + weekStart);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(weekStart);
        calendar.set(7, weekStart);
        calendar.add(5, -1);
        String str = simpleDateFormat.format(calendar.getTime());
        calendar.add(5, -6);
        return new Pair<>(simpleDateFormat.format(calendar.getTime()), str);
    }

    public static String getEndDayOfLastWeek() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(7);
        int weekStart = RunTimeUtil.generateDefaultWeekSetting(RunTimeUtil.getInstance().getUserId()).getWeekStart();
        if (weekStart == 7 && weekStart != i) {
            weekStart = 0;
        }
        calendar.add(5, (weekStart - i) - 1);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getBeginDayOfLastWeek() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(7);
        int weekStart = RunTimeUtil.generateDefaultWeekSetting(RunTimeUtil.getInstance().getUserId()).getWeekStart();
        if (weekStart == 7 && weekStart != i) {
            weekStart = 0;
        }
        calendar.add(5, (weekStart - i) - 7);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getBeginDayOfWeek() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(7);
        int weekStart = RunTimeUtil.generateDefaultWeekSetting(RunTimeUtil.getInstance().getUserId()).getWeekStart();
        if (weekStart == 7 && weekStart != i) {
            weekStart = 0;
        }
        calendar.add(5, weekStart - i);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static int daysBetween(String str) {
        long timeInMillis;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(str));
            long timeInMillis2 = calendar.getTimeInMillis();
            calendar.setTime(getDate());
            timeInMillis = (calendar.getTimeInMillis() - timeInMillis2) / DateUtil.DAY;
        } catch (Exception e2) {
            e2.printStackTrace();
            timeInMillis = 0;
        }
        return Integer.parseInt(String.valueOf(timeInMillis));
    }

    public static int second2Hour(int i) {
        return (i % 86400) / 3600;
    }

    public static int second2Min(int i) {
        return (i % 3600) / 60;
    }

    public static int dayForWeek(String str) {
        Date dateString2Date = DateUtil.string2Date(str, DateUtil.DATE_FORMAT_YMD);
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int[] iArr = {7, 1, 2, 3, 4, 5, 6};
        calendar.setTime(dateString2Date);
        int i = calendar.get(7) - 1;
        if (i < 0) {
            i = 0;
        }
        return iArr[i];
    }

    public static String getDateYMDTime(int i, int i2, int i3) {
        return new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD).format(getDate(i, i2, i3, 0, 0, 0));
    }
}