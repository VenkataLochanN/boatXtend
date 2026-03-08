package com.ido.life.util;

import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.common.log.CommonLogUtil;

/* JADX INFO: loaded from: classes3.dex */
public class HealthCareUtil {
    public static final int DEFAULT_START_YEAR = 1990;
    public static final String MENSTRUAL = "menstrual";
    public static final int MENSTRUAL_CYCLE_DEFAULT = 28;
    public static final int MENSTRUAL_CYCLE_MAX = 90;
    public static final int MENSTRUAL_CYCLE_MIN = 20;
    public static final int MENSTRUAL_LENGTH_DEFAULT = 7;
    public static final int MENSTRUAL_LENGTH_MAX = 14;
    public static final int MENSTRUAL_LENGTH_MIN = 1;
    public static final String MENSTRUAL_REMIND = "menstrual_remind";
    public static final int MENSTRUAL_REMIND_DAYS_DEFAULT = 3;
    public static final int MENSTRUAL_REMIND_MIN = 1;
    public static final int MENSTRUAL_SET_CODE = 100;
    public static final int OVULATION_REMIND_DAYS_DEFAULT = 3;
    public static final int OVULATION_REMIND_MIN = 1;
    public static final int PREGNANCY_REMIND_DAYS_DEFAULT = 3;
    public static final int PREGNANCY_REMIND_MIN = 1;
    public static final int REMIND_SET_CODE = 200;
    public static final int REMIND_TIME_HOUR_DEFAULT = 20;
    public static final int REMIND_TIME_MIN_DEFAULT = 0;
    public static final int TYPE_ALL = 0;
    public static final int TYPE_MENSTRUAL = 100;
    public static final int TYPE_OVULATION = 200;
    public static final int VISIBLE_COUNT = 3;

    public static String[] initArray(int i, int i2, String str) {
        String[] strArr = new String[Math.abs(i2 - i) + 1];
        for (int i3 = 0; i3 < strArr.length; i3++) {
            strArr[i3] = (i + i3) + str;
        }
        return strArr;
    }

    public static boolean compareAttributes(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return true;
        }
        Class<?> cls = obj.getClass();
        Class<?> cls2 = obj2.getClass();
        CommonLogUtil.d("obj1Class = " + cls + " ; obj2Class = " + cls2 + " ; 是否相等 = " + cls.equals(cls2));
        if (cls.equals(cls2)) {
            return obj.toString().equals(obj2.toString());
        }
        return false;
    }

    public static boolean compareMenstrualRemind(MenstrualRemind menstrualRemind, MenstrualRemind menstrualRemind2) {
        if (menstrualRemind == null || menstrualRemind2 == null) {
            return true;
        }
        Class<?> cls = menstrualRemind.getClass();
        Class<?> cls2 = menstrualRemind2.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("remind1Class = ");
        sb.append(cls);
        sb.append(" ; remind2Class = ");
        sb.append(cls2);
        sb.append(" ; 是否相等 = ");
        sb.append(cls == cls2);
        CommonLogUtil.d(sb.toString());
        return menstrualRemind.toString().equals(menstrualRemind2.toString());
    }

    public static String getNextMenstrualRemindTime(String str, int[] iArr, int i, int i2) {
        if (iArr == null || iArr.length < 2) {
            iArr = new int[2];
        }
        String specifiedDayBefore = DateUtil.getSpecifiedDayBefore(getNextMenstrualTime(str, iArr, i2), DateUtil.DATE_FORMAT_YMDHm, i);
        while (DateUtil.isExpire(DateUtil.string2Date(specifiedDayBefore, DateUtil.DATE_FORMAT_YMDHm))) {
            specifiedDayBefore = DateUtil.getSpecifiedDayBefore(specifiedDayBefore, DateUtil.DATE_FORMAT_YMDHm, -i2);
        }
        return specifiedDayBefore;
    }

    public static String getNextMenstrualTime(String str, int[] iArr, int i) {
        if (iArr == null || iArr.length < 2) {
            iArr = new int[2];
        }
        String specifiedDayBefore = str + " " + TimeUtil.formatTime(iArr[0], iArr[1]);
        while (DateUtil.isExpire(DateUtil.string2Date(specifiedDayBefore, DateUtil.DATE_FORMAT_YMDHm))) {
            specifiedDayBefore = DateUtil.getSpecifiedDayBefore(specifiedDayBefore, DateUtil.DATE_FORMAT_YMDHm, -i);
        }
        return specifiedDayBefore;
    }

    public static String getNextOvulationRemindTime(String str, int[] iArr, int i, int i2) {
        String specifiedDayBefore = DateUtil.getSpecifiedDayBefore(getNextMenstrualTime(str, iArr, i2), DateUtil.DATE_FORMAT_YMDHm, i + 14);
        while (DateUtil.isExpire(DateUtil.string2Date(specifiedDayBefore, DateUtil.DATE_FORMAT_YMDHm))) {
            specifiedDayBefore = DateUtil.getSpecifiedDayBefore(specifiedDayBefore, DateUtil.DATE_FORMAT_YMDHm, -i2);
        }
        return specifiedDayBefore;
    }

    public static Menstrual initTempMenstrual(Menstrual menstrual) {
        Menstrual menstrual2 = new Menstrual();
        if (menstrual == null || menstrual.menstrual_length == 0) {
            menstrual2.menstrual_length = 7;
            menstrual2.menstrual_cycle = 28;
            int[] currentDate = DateUtil.getCurrentDate();
            menstrual2.last_menstrual_year = currentDate[0];
            menstrual2.last_menstrual_month = currentDate[1];
            menstrual2.last_menstrual_day = currentDate[2];
        } else {
            menstrual2.on_off = menstrual.on_off;
            menstrual2.menstrual_length = menstrual.menstrual_length;
            menstrual2.menstrual_cycle = menstrual.menstrual_cycle;
            menstrual2.last_menstrual_year = menstrual.last_menstrual_year;
            menstrual2.last_menstrual_month = menstrual.last_menstrual_month;
            menstrual2.last_menstrual_day = menstrual.last_menstrual_day;
        }
        return menstrual2;
    }

    public static MenstrualRemind initTempMenstrualRemind(MenstrualRemind menstrualRemind) {
        MenstrualRemind menstrualRemind2 = new MenstrualRemind();
        if (menstrualRemind == null || menstrualRemind.start_day == 0) {
            menstrualRemind2.start_day = 3;
            menstrualRemind2.ovulation_day = 3;
            menstrualRemind2.hour = 20;
            menstrualRemind2.minute = 0;
        } else {
            menstrualRemind2.start_day = menstrualRemind.start_day;
            int i = menstrualRemind.ovulation_day;
            menstrualRemind2.ovulation_day = i != 0 ? i : 3;
            menstrualRemind2.hour = menstrualRemind.hour;
            menstrualRemind2.minute = menstrualRemind.minute;
        }
        return menstrualRemind2;
    }
}