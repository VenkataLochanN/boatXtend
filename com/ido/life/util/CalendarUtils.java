package com.ido.life.util;

import android.text.format.DateFormat;
import com.boat.Xtend.two.R;
import com.google.android.material.timepicker.TimeModel;
import com.ido.common.IdoApp;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.constants.Constants;
import com.realsil.sdk.dfu.DfuConstants;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: CalendarUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bJ\u0016\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bJ\u0016\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bJ\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000bH\u0007J\u0016\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tJ\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0006J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\u001b\u001a\u00020\u001c¨\u0006\u001d"}, d2 = {"Lcom/ido/life/util/CalendarUtils;", "", "()V", "formatTime", "", "time", "", "getDate", Constants.AppPackage.CALENDAR, "Ljava/util/Calendar;", "getLocalTimezone", "Ljava/util/TimeZone;", "getTime", "getTimezoneDifferenceInMillSec", "local", "comparedZone", "getTimezoneDifferenceInMinute", "getTimezoneDifferenceStr", "getTimezoneOffsetInMin", "zone", "getTypeOfDay", "comparedCalendar", "getTypeOfDayStr", "dayType", "getWeek", "week", "getWorldTimeDate", "isTimeFormat24", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CalendarUtils {
    public static final CalendarUtils INSTANCE = new CalendarUtils();

    private CalendarUtils() {
    }

    public final TimeZone getLocalTimezone() {
        TimeZone timeZone = TimeZone.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(timeZone, "TimeZone.getDefault()");
        return timeZone;
    }

    public final String getTimezoneDifferenceStr(TimeZone local, TimeZone comparedZone) {
        String string;
        Intrinsics.checkParameterIsNotNull(local, "local");
        Intrinsics.checkParameterIsNotNull(comparedZone, "comparedZone");
        int timezoneDifferenceInMinute = getTimezoneDifferenceInMinute(local, comparedZone);
        Calendar calendar = Calendar.getInstance(local);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance(local)");
        Calendar calendar2 = Calendar.getInstance(comparedZone);
        Intrinsics.checkExpressionValueIsNotNull(calendar2, "Calendar.getInstance(comparedZone)");
        int typeOfDay = getTypeOfDay(calendar, calendar2);
        int iAbs = Math.abs(timezoneDifferenceInMinute) / 60;
        if (Math.abs(timezoneDifferenceInMinute) % 60 > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(iAbs);
            sb.append(':');
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Integer.valueOf(Math.abs(timezoneDifferenceInMinute) % 60)};
            String str = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
            sb.append(str);
            string = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(iAbs);
            sb2.append('H');
            string = sb2.toString();
        }
        String languageText = LanguageUtil.getLanguageText(R.string.world_time);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(R.string.world_time)");
        Object[] objArr2 = new Object[3];
        objArr2[0] = getTypeOfDayStr(typeOfDay);
        objArr2[1] = timezoneDifferenceInMinute >= 0 ? "+" : "-";
        objArr2[2] = string;
        String str2 = String.format(languageText, Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
        return str2;
    }

    public final int getTimezoneDifferenceInMinute(TimeZone local, TimeZone comparedZone) {
        Intrinsics.checkParameterIsNotNull(local, "local");
        Intrinsics.checkParameterIsNotNull(comparedZone, "comparedZone");
        return getTimezoneDifferenceInMillSec(local, comparedZone) / DfuConstants.DFU_UPLOAD_IMAGE_TIMEOUT;
    }

    public final int getTimezoneDifferenceInMillSec(TimeZone local, TimeZone comparedZone) {
        Intrinsics.checkParameterIsNotNull(local, "local");
        Intrinsics.checkParameterIsNotNull(comparedZone, "comparedZone");
        Date date = new Date();
        return (comparedZone.getRawOffset() + (comparedZone.inDaylightTime(date) ? comparedZone.getDSTSavings() : 0)) - (local.getRawOffset() + (local.inDaylightTime(date) ? local.getDSTSavings() : 0));
    }

    @JvmStatic
    public static final int getTimezoneOffsetInMin(TimeZone zone) {
        Intrinsics.checkParameterIsNotNull(zone, "zone");
        return (zone.getRawOffset() + (zone.inDaylightTime(new Date()) ? zone.getDSTSavings() : 0)) / DfuConstants.DFU_UPLOAD_IMAGE_TIMEOUT;
    }

    public final String getTime(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        int i = calendar.get(11);
        int i2 = calendar.get(12);
        int i3 = calendar.get(9);
        if (isTimeFormat24()) {
            return formatTime(i) + ':' + formatTime(i2);
        }
        String languageText = LanguageUtil.getLanguageText(i3 == 0 ? R.string.world_time_time_am : R.string.world_time_time_pm);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…tring.world_time_time_pm)");
        Object[] objArr = {formatTime(TimeUtil.format24To12(i)), formatTime(i2)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        return str;
    }

    public final boolean isTimeFormat24() {
        return DateFormat.is24HourFormat(IdoApp.getAppContext());
    }

    public final String formatTime(int time) {
        String str = new DecimalFormat("00").format(Integer.valueOf(time));
        Intrinsics.checkExpressionValueIsNotNull(str, "formatter.format(time)");
        return str;
    }

    public final String getWorldTimeDate(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        int i = calendar.get(2) + 1;
        int i2 = calendar.get(5);
        int i3 = calendar.get(7);
        String languageText = LanguageUtil.getLanguageText(R.string.world_time_date_week);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ing.world_time_date_week)");
        Object[] objArr = {Integer.valueOf(i), Integer.valueOf(i2), getWeek(i3)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        return str;
    }

    public final String getDate(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append('-');
        sb.append(i2);
        sb.append('-');
        sb.append(i3);
        return sb.toString();
    }

    private final String getWeek(int week) {
        int i;
        switch (week) {
            case 1:
                i = R.string.public_time_sunday;
                break;
            case 2:
                i = R.string.public_time_monday;
                break;
            case 3:
                i = R.string.public_time_tuesday;
                break;
            case 4:
                i = R.string.public_time_wednesday;
                break;
            case 5:
                i = R.string.public_time_thursday;
                break;
            case 6:
                i = R.string.public_time_friday;
                break;
            case 7:
                i = R.string.public_time_saturday;
                break;
            default:
                i = -1;
                break;
        }
        if (i <= 0) {
            return "";
        }
        String languageText = LanguageUtil.getLanguageText(i);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(weekResId)");
        return languageText;
    }

    public final int getTypeOfDay(Calendar local, Calendar comparedCalendar) {
        Intrinsics.checkParameterIsNotNull(local, "local");
        Intrinsics.checkParameterIsNotNull(comparedCalendar, "comparedCalendar");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, comparedCalendar.get(1));
        calendar.set(2, comparedCalendar.get(2));
        calendar.set(5, comparedCalendar.get(5));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1, local.get(1));
        calendar2.set(2, local.get(2));
        calendar2.set(5, local.get(5));
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(1, calendar2.get(1));
        calendar3.set(2, calendar2.get(2));
        calendar3.set(5, calendar2.get(5) - 1);
        calendar3.set(11, 0);
        calendar3.set(12, 0);
        calendar3.set(13, 0);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(1, calendar2.get(1));
        calendar4.set(2, calendar2.get(2));
        calendar4.set(5, calendar2.get(5) + 1);
        calendar4.set(11, 0);
        calendar4.set(12, 0);
        calendar4.set(13, 0);
        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(1, calendar2.get(1));
        calendar5.set(2, calendar2.get(2));
        calendar5.set(5, calendar2.get(5) + 2);
        calendar5.set(11, 0);
        calendar5.set(12, 0);
        calendar5.set(13, 0);
        if (calendar.after(calendar2) && calendar.before(calendar4)) {
            return 0;
        }
        if (calendar.before(calendar2) && calendar.after(calendar3)) {
            return -1;
        }
        return (calendar.before(calendar5) && calendar.after(calendar4)) ? 1 : 2;
    }

    public final String getTypeOfDayStr(int dayType) {
        int i = -1;
        if (dayType == -1) {
            i = R.string.public_time_yesterday;
        } else if (dayType == 0) {
            i = R.string.public_time_today;
        } else if (dayType == 1) {
            i = R.string.public_time_tomorrow;
        }
        if (i <= 0) {
            return "";
        }
        String languageText = LanguageUtil.getLanguageText(i);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(resId)");
        return languageText;
    }
}