package com.ido.life.module.home.chartdetail.vertical;

import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChartDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0005J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0005J\u000e\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0005J\u000e\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0005J\u000e\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010J\u000e\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0005J\u000e\u0010 \u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0005J\u000e\u0010!\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0005J\u0006\u0010\"\u001a\u00020#J\u0018\u0010$\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0018\u0010%\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0018\u0010&\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0018\u0010'\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0005H\u0002R\u0019\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006)"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/ChartDetailPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/ChartDetailView;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "mCurrentDate", "Ljava/util/Calendar;", "getMCurrentDate", "()Ljava/util/Calendar;", "setMCurrentDate", "(Ljava/util/Calendar;)V", "mWeekStart", "", "getMWeekStart", "()I", "setMWeekStart", "(I)V", "caluteDateByDateTypeChanged", "from", "to", "baseDate", "caluteDayOffset", "selectDate", "caluteDayOffsetFromYear", "caluteMonthOffset", "caluteNearestDataDateOffset", "dateType", "caluteWeekOffset", "caluteWeekOffsetFromYear", "caluteYearOffset", "isSupportVolume", "", "switchDateTypeFromDay", "switchDateTypeFromMonth", "switchDateTypeFromWeek", "switchDateTypeFromYear", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ChartDetailPresenter extends BasePresenter<ChartDetailView> {
    public static final int DEFAULT_OFFSET = Integer.MAX_VALUE;
    private final String TAG = ChartDetailPresenter.class.getSimpleName();
    private Calendar mCurrentDate;
    private int mWeekStart;

    public ChartDetailPresenter() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance(Locale.CHINA)");
        this.mCurrentDate = calendar;
        this.mWeekStart = 1;
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        this.mWeekStart = runTimeUtil.getWeekStart();
        this.mCurrentDate.set(11, 1);
        this.mCurrentDate.set(12, 0);
        this.mCurrentDate.set(13, 0);
        this.mCurrentDate.set(14, 0);
        this.mCurrentDate.setFirstDayOfWeek(this.mWeekStart);
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final Calendar getMCurrentDate() {
        return this.mCurrentDate;
    }

    public final void setMCurrentDate(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "<set-?>");
        this.mCurrentDate = calendar;
    }

    public final int getMWeekStart() {
        return this.mWeekStart;
    }

    public final void setMWeekStart(int i) {
        this.mWeekStart = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0139  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int caluteNearestDataDateOffset(int r5) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.chartdetail.vertical.ChartDetailPresenter.caluteNearestDataDateOffset(int):int");
    }

    public final int caluteDayOffset(String selectDate) {
        Intrinsics.checkParameterIsNotNull(selectDate, "selectDate");
        int timeInMillis = Integer.MAX_VALUE;
        if (selectDate.length() == 0) {
            CommonLogUtil.d(this.TAG, "selectDate 不能为null");
            return Integer.MAX_VALUE;
        }
        try {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(selectDate, DateUtil.DATE_FORMAT_YMD));
            calendar.set(11, 1);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.setFirstDayOfWeek(this.mWeekStart);
            long j = 60;
            timeInMillis = (int) (((((calendar.getTimeInMillis() - this.mCurrentDate.getTimeInMillis()) / ((long) 1000)) / j) / j) / ((long) 24));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        CommonLogUtil.d(this.TAG, "caluteDayOffset(selectDate=" + selectDate + ",offset=" + timeInMillis + ')');
        return timeInMillis;
    }

    public final int caluteDayOffsetFromYear(String selectDate) {
        Intrinsics.checkParameterIsNotNull(selectDate, "selectDate");
        int timeInMillis = 0;
        if (selectDate.length() == 0) {
            CommonLogUtil.d(this.TAG, "selectDate 不能为null");
            return Integer.MAX_VALUE;
        }
        try {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(selectDate, DateUtil.DATE_FORMAT_YMD));
            calendar.set(11, 1);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.setFirstDayOfWeek(this.mWeekStart);
            calendar.set(5, 16);
            if (calendar.getTimeInMillis() <= this.mCurrentDate.getTimeInMillis()) {
                long j = 60;
                timeInMillis = (int) (((((calendar.getTimeInMillis() - this.mCurrentDate.getTimeInMillis()) / ((long) 1000)) / j) / j) / ((long) 24));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            timeInMillis = Integer.MAX_VALUE;
        }
        CommonLogUtil.d(this.TAG, "caluteDayOffset(selectDate=" + selectDate + ",offset=" + timeInMillis + ')');
        return timeInMillis;
    }

    public final int caluteWeekOffset(String selectDate) {
        Calendar calendar;
        Object objClone;
        Intrinsics.checkParameterIsNotNull(selectDate, "selectDate");
        int i = Integer.MAX_VALUE;
        if (selectDate.length() == 0) {
            CommonLogUtil.d(this.TAG, "selectDate 不能为null");
            return Integer.MAX_VALUE;
        }
        try {
            calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(selectDate, DateUtil.DATE_FORMAT_YMD));
            calendar.set(11, 1);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.setFirstDayOfWeek(this.mWeekStart);
            calendar.set(7, this.mWeekStart);
            objClone = this.mCurrentDate.clone();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (objClone == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
        }
        Calendar calendar2 = (Calendar) objClone;
        calendar2.set(7, this.mWeekStart);
        long timeInMillis = (calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / ((long) 1000);
        long j = 60;
        i = (int) ((((timeInMillis / j) / j) / ((long) 24)) / ((long) 7));
        CommonLogUtil.d(this.TAG, "caluteWeekOffset(selectDate=" + selectDate + ",offset=" + i + ')');
        return i;
    }

    public final int caluteWeekOffsetFromYear(String selectDate) {
        Intrinsics.checkParameterIsNotNull(selectDate, "selectDate");
        int timeInMillis = 0;
        if (selectDate.length() == 0) {
            CommonLogUtil.d(this.TAG, "selectDate 不能为null");
            return Integer.MAX_VALUE;
        }
        try {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(selectDate, DateUtil.DATE_FORMAT_YMD));
            calendar.set(11, 1);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.setFirstDayOfWeek(this.mWeekStart);
            calendar.set(4, calendar.getActualMaximum(4));
            calendar.set(7, this.mWeekStart);
            if (calendar.getTimeInMillis() <= this.mCurrentDate.getTimeInMillis()) {
                long j = 60;
                timeInMillis = (int) ((((((calendar.getTimeInMillis() - this.mCurrentDate.getTimeInMillis()) / ((long) 1000)) / j) / j) / ((long) 24)) / ((long) 7));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            timeInMillis = Integer.MAX_VALUE;
        }
        CommonLogUtil.d(this.TAG, "caluteWeekOffset(selectDate=" + selectDate + ",offset=" + timeInMillis + ')');
        return timeInMillis;
    }

    public final int caluteMonthOffset(String selectDate) {
        Intrinsics.checkParameterIsNotNull(selectDate, "selectDate");
        int i = Integer.MAX_VALUE;
        if (selectDate.length() == 0) {
            CommonLogUtil.d(this.TAG, "selectDate 不能为空");
            return Integer.MAX_VALUE;
        }
        try {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(selectDate, DateUtil.DATE_FORMAT_YMD));
            calendar.set(11, 1);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.setFirstDayOfWeek(this.mWeekStart);
            i = (((calendar.get(1) - this.mCurrentDate.get(1)) * 12) + calendar.get(2)) - this.mCurrentDate.get(2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        CommonLogUtil.d(this.TAG, "caluteMonthOffset(selectDate=" + selectDate + ",offset=" + i + ')');
        return i;
    }

    public final int caluteYearOffset(String selectDate) {
        Intrinsics.checkParameterIsNotNull(selectDate, "selectDate");
        int i = Integer.MAX_VALUE;
        if (selectDate.length() == 0) {
            CommonLogUtil.d(this.TAG, "selectDate 不能为空");
            return Integer.MAX_VALUE;
        }
        try {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(selectDate, DateUtil.DATE_FORMAT_YMD));
            i = calendar.get(1) - this.mCurrentDate.get(1);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        CommonLogUtil.d(this.TAG, "caluteYearOffset(selectDate=" + selectDate + ",offset=" + i + ')');
        return i;
    }

    public final String caluteDateByDateTypeChanged(int from, int to, String baseDate) {
        String strSwitchDateTypeFromDay;
        Intrinsics.checkParameterIsNotNull(baseDate, "baseDate");
        if (from != to) {
            if (!(baseDate.length() == 0)) {
                if (from == 1) {
                    strSwitchDateTypeFromDay = switchDateTypeFromDay(to, baseDate);
                } else if (from == 2) {
                    strSwitchDateTypeFromDay = switchDateTypeFromWeek(to, baseDate);
                } else if (from != 3) {
                    strSwitchDateTypeFromDay = from != 4 ? baseDate : switchDateTypeFromYear(to, baseDate);
                } else {
                    strSwitchDateTypeFromDay = switchDateTypeFromMonth(to, baseDate);
                }
                CommonLogUtil.d(this.TAG, "caluteDateByDateTypeChanged(from=" + from + ",to=" + to + ",baseDate=" + baseDate + ",date=" + strSwitchDateTypeFromDay + ')');
                return strSwitchDateTypeFromDay;
            }
        }
        return baseDate;
    }

    private final String switchDateTypeFromYear(int to, String baseDate) {
        String str;
        try {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(baseDate, DateUtil.DATE_FORMAT_YMD));
            calendar.set(11, 1);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.setFirstDayOfWeek(this.mWeekStart);
            int i = this.mCurrentDate.get(1);
            int i2 = calendar.get(1);
            if (to != 1) {
                if (to != 2) {
                    if (to == 3) {
                        if (i <= i2) {
                            calendar = this.mCurrentDate;
                        } else {
                            calendar.set(2, calendar.getActualMaximum(2));
                        }
                    }
                } else if (i <= i2) {
                    calendar = this.mCurrentDate;
                } else {
                    calendar.set(3, calendar.getActualMaximum(3));
                    calendar.set(7, this.mWeekStart);
                }
            } else if (i <= i2) {
                calendar = this.mCurrentDate;
            } else {
                calendar.set(6, calendar.getActualMaximum(6));
            }
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            str = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
        } catch (Exception e2) {
            e2.printStackTrace();
            str = baseDate;
        }
        CommonLogUtil.d(this.TAG, "switchDateTypeFromYear(to=" + to + ",selectDate=" + baseDate + ",date=" + str + ')');
        return str;
    }

    private final String switchDateTypeFromMonth(int to, String baseDate) {
        String str;
        Calendar calendar;
        try {
            calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(baseDate, DateUtil.DATE_FORMAT_YMD));
            calendar.set(11, 1);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.setFirstDayOfWeek(this.mWeekStart);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (to == 1) {
            calendar.set(5, calendar.getActualMaximum(5));
            if (calendar.getTimeInMillis() > this.mCurrentDate.getTimeInMillis()) {
                calendar = this.mCurrentDate;
            }
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            str = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
        } else if (to != 2) {
            str = baseDate;
        } else {
            calendar.set(4, calendar.getActualMaximum(4));
            calendar.set(7, this.mWeekStart);
            calendar.add(5, 6);
            if (calendar.getTimeInMillis() > this.mCurrentDate.getTimeInMillis()) {
                calendar = this.mCurrentDate;
            }
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            str = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
        }
        CommonLogUtil.d(this.TAG, "switchDateTypeFromMonth(to=" + to + ",selectDate=" + baseDate + ",date=" + str + ')');
        return str;
    }

    private final String switchDateTypeFromWeek(int to, String baseDate) {
        String str;
        Calendar calendar;
        try {
            calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(baseDate, DateUtil.DATE_FORMAT_YMD));
            calendar.set(11, 1);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.setFirstDayOfWeek(this.mWeekStart);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (to == 1 || to == 3) {
            calendar.set(7, calendar.getActualMaximum(7));
            if (calendar.getTimeInMillis() > this.mCurrentDate.getTimeInMillis()) {
                calendar = this.mCurrentDate;
            }
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            str = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
            CommonLogUtil.d(this.TAG, "switchDateTypeFromWeek(to=" + to + ",selectDate=" + baseDate + ",date=" + str + ')');
            return str;
        }
        str = baseDate;
        CommonLogUtil.d(this.TAG, "switchDateTypeFromWeek(to=" + to + ",selectDate=" + baseDate + ",date=" + str + ')');
        return str;
    }

    private final String switchDateTypeFromDay(int to, String baseDate) {
        CommonLogUtil.d(this.TAG, "switchDateTypeFromDay(to=" + to + ",selectDate=" + baseDate + ",date=" + baseDate + ')');
        return baseDate;
    }

    public final boolean isSupportVolume() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.V3_health_sync_noise;
    }
}