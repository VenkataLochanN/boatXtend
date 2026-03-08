package com.ido.life.module.home.menstrualcycle;

import android.database.Cursor;
import com.ido.life.constants.Constants;
import com.ido.life.customview.MenstruationCalendar;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenstrualUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002\u001a\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a,\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\f\u001a\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005\u001a\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007\u001a\u0016\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\f\u001a\u000e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a\u001a\u000e\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a¨\u0006\u001c"}, d2 = {"closeCursor", "", "cursor", "Landroid/database/Cursor;", "convertToCommonCalendar", "Ljava/util/Calendar;", "date", "", "generateCalendarData", "", "Lcom/ido/life/customview/MenstruationCalendar$DateInfo;", "year", "", "month", "autoFill", "", "weekStart", "getDays", "startCalendar", "endCalendar", "startDate", "endDate", "getDistenceWeekStartDayCount", Constants.AppPackage.CALENDAR, "getMensSettingEndDate", "userId", "", "getMensSettingStartDate", "app_release"}, k = 2, mv = {1, 1, 16})
public final class MenstrualUtilKt {
    public static final List<MenstruationCalendar.DateInfo> generateCalendarData(int i, int i2, boolean z, int i3) {
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.set(5, 1);
        int actualMaximum = calendar.getActualMaximum(5);
        Object objClone = calendar.clone();
        if (objClone == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        int distenceWeekStartDayCount = getDistenceWeekStartDayCount((Calendar) objClone, i3);
        calendar.add(5, -distenceWeekStartDayCount);
        boolean z2 = false;
        int i4 = 1;
        for (int i5 = 42; i4 <= i5; i5 = 42) {
            int i6 = calendar.get(5);
            if (i4 <= distenceWeekStartDayCount || z2) {
                arrayList.add(new MenstruationCalendar.DateInfo(i6, z ? String.valueOf(i6) : "", MenstruationCalendar.Type.FILL, false, MenstruationCalendar.Type.FILL));
                if (i4 % 7 == 0) {
                    break;
                }
                calendar.add(5, 1);
                i4++;
            } else {
                arrayList.add(new MenstruationCalendar.DateInfo(i6, String.valueOf(i6), MenstruationCalendar.Type.NORMAL, true, MenstruationCalendar.Type.NORMAL));
                if (i6 != actualMaximum) {
                    continue;
                } else {
                    if (i4 % 7 == 0) {
                        break;
                    }
                    z2 = true;
                }
                calendar.add(5, 1);
                i4++;
            }
        }
        return arrayList;
    }

    public static final int getDistenceWeekStartDayCount(Calendar calendar, int i) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        int i2 = 0;
        while (calendar.get(7) != i) {
            calendar.add(5, -1);
            i2++;
        }
        return i2;
    }

    public static final String getMensSettingStartDate(long j) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(2, -24);
        LifeCycleItemBean lifeCycleItemBeanQueryOldestLifeCycle = GreenDaoUtil.queryOldestLifeCycle(j);
        if (lifeCycleItemBeanQueryOldestLifeCycle == null) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(minCalen…DateUtil.DATE_FORMAT_YMD)");
            return str;
        }
        Calendar oldestCalender = Calendar.getInstance(Locale.CHINA);
        try {
            Intrinsics.checkExpressionValueIsNotNull(oldestCalender, "oldestCalender");
            oldestCalender.setTime(DateUtil.string2Date(lifeCycleItemBeanQueryOldestLifeCycle.getMonth(), DateUtil.DATE_FORMAT_YM_3));
            if (oldestCalender.before(calendar)) {
                String str2 = DateUtil.format(oldestCalender, DateUtil.DATE_FORMAT_YMD);
                Intrinsics.checkExpressionValueIsNotNull(str2, "DateUtil.format(\n       …DATE_FORMAT_YMD\n        )");
                return str2;
            }
        } catch (Exception unused) {
        }
        String str3 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        Intrinsics.checkExpressionValueIsNotNull(str3, "DateUtil.format(minCalen…DateUtil.DATE_FORMAT_YMD)");
        return str3;
    }

    public static final String getMensSettingEndDate(long j) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(2, 1);
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
        return str;
    }

    private static final void closeCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    public static final int getDays(String startDate, String endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
        startCalendar.setTime(DateUtil.string2Date(startDate, DateUtil.DATE_FORMAT_YMD));
        Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
        endCalendar.setTime(DateUtil.string2Date(endDate, DateUtil.DATE_FORMAT_YMD));
        return getDays(startCalendar, endCalendar) + 1;
    }

    public static final int getDays(Calendar startCalendar, Calendar endCalendar) {
        Intrinsics.checkParameterIsNotNull(startCalendar, "startCalendar");
        Intrinsics.checkParameterIsNotNull(endCalendar, "endCalendar");
        startCalendar.set(11, 0);
        startCalendar.set(12, 0);
        startCalendar.set(13, 0);
        startCalendar.set(14, 0);
        endCalendar.set(11, 0);
        endCalendar.set(12, 0);
        endCalendar.set(13, 0);
        endCalendar.set(14, 0);
        return (int) ((endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) / ((long) 86400000));
    }

    public static final Calendar convertToCommonCalendar(String date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        try {
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(date, DateUtil.DATE_FORMAT_YMD));
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
        } catch (Exception unused) {
        }
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        return calendar;
    }
}