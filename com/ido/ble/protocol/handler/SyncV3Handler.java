package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.callback.SyncV3CallBack;
import com.ido.ble.common.TimeUtil;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthBloodPressureV3;
import com.ido.ble.data.manage.database.HealthGpsItemV3;
import com.ido.ble.data.manage.database.HealthGpsV3;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthHeartRateSecondItem;
import com.ido.ble.data.manage.database.HealthNoise;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthSleepV3;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSportV3;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.data.manage.database.HealthTemperature;
import com.ido.ble.logs.LogTool;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class SyncV3Handler {

    static class HealthPressureWrapper extends HealthPressure {
        List<HealthPressureItem> items;

        HealthPressureWrapper() {
        }
    }

    static class HealthSpO2Wrapper extends HealthSpO2 {
        List<HealthSpO2Item> items;

        HealthSpO2Wrapper() {
        }
    }

    SyncV3Handler() {
    }

    private static void handleActivityV3Data(byte[] bArr) {
        String str;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            str = "[SYNC_DATA] handleActivityV3Data, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleActivityV3Data, jsonData=" + strD);
            HealthActivityV3 healthActivityV3 = (HealthActivityV3) com.ido.ble.common.k.c(strD, HealthActivityV3.class);
            if (healthActivityV3 == null) {
                str = "[SYNC_DATA] handleActivityV3Data, healthActivityV3 is null";
            } else {
                if (healthActivityV3.year != 0) {
                    if (("" + healthActivityV3.year).length() == 2) {
                        healthActivityV3.year += 2000;
                    }
                    SyncV3CallBack.onGetHealthActivityV3Data(healthActivityV3);
                    return;
                }
                str = "[SYNC_DATA] handleActivityV3Data, year is 0";
            }
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, str);
    }

    private static void handleBloodPressureData(byte[] bArr) {
        String str;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            str = "[SYNC_DATA] handleBloodPressureData, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleBloodPressureData, jsonData=" + strD);
            HealthBloodPressureV3 healthBloodPressureV3 = (HealthBloodPressureV3) com.ido.ble.common.k.c(strD, HealthBloodPressureV3.class);
            if (healthBloodPressureV3 != null) {
                SyncV3CallBack.onGetHealthBloodPressure(healthBloodPressureV3);
                return;
            }
            str = "[SYNC_DATA] handleBloodPressureData, BloodPressureData is null";
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, str);
    }

    private static List<HealthGpsItemV3> handleHealthGpsItemV3Data(HealthGpsV3 healthGpsV3) {
        double dA;
        ArrayList arrayList = new ArrayList();
        List<String> list = healthGpsV3.stringItems;
        if (list != null && list.size() != 0) {
            for (String str : healthGpsV3.stringItems) {
                if (str.contains(AppInfo.DELIM)) {
                    String[] strArrSplit = str.split(AppInfo.DELIM);
                    double dA2 = 0.0d;
                    try {
                        dA = com.ido.ble.h.c.a(strArrSplit[0], "E");
                        try {
                            dA2 = com.ido.ble.h.c.a(strArrSplit[1], "N");
                        } catch (Exception e2) {
                            e = e2;
                            LogTool.b(com.ido.ble.logs.a.j, "[handleHealthGpsItemV3Data]" + e.toString());
                        }
                    } catch (Exception e3) {
                        e = e3;
                        dA = 0.0d;
                    }
                    HealthGpsItemV3 healthGpsItemV3 = new HealthGpsItemV3();
                    healthGpsItemV3.latitude = Double.valueOf(dA2);
                    healthGpsItemV3.longitude = Double.valueOf(dA);
                    arrayList.add(healthGpsItemV3);
                }
            }
            LogTool.d(com.ido.ble.logs.a.j, "[handleHealthGpsItemV3Data] size = " + arrayList.size());
        }
        return arrayList;
    }

    private static void handleHealthGpsV3Data(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleHealthGpsV3Data, jsonString is null");
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleHealthGpsV3Data, jsonData=" + strD);
        HealthGpsV3 healthGpsV3 = (HealthGpsV3) com.ido.ble.common.k.c(strD, HealthGpsV3.class);
        if (healthGpsV3 == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleHealthGpsV3Data, healthGpsV3 is null");
        } else {
            if (healthGpsV3.year == 0) {
                LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleHealthGpsV3Data, healthGpsV3.year = 0");
                return;
            }
            healthGpsV3.items = new ArrayList();
            healthGpsV3.items.addAll(handleHealthGpsItemV3Data(healthGpsV3));
            SyncV3CallBack.onGetHealthGpsV3Data(healthGpsV3);
        }
    }

    private static void handleHealthSleepV3Data(byte[] bArr) {
        String str;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            str = "[SYNC_DATA] handleHealthSleepV3Data, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleHealthSleepV3Data, jsonData=" + strD);
            HealthSleepV3 healthSleepV3 = (HealthSleepV3) com.ido.ble.common.k.c(strD, HealthSleepV3.class);
            if (healthSleepV3 != null) {
                SyncV3CallBack.onGetHealthSleepV3Data(healthSleepV3);
                return;
            }
            str = "[SYNC_DATA] handleHealthSleepV3Data, healthSleepV3 is null";
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, str);
    }

    private static void handleHealthSportV3Data(byte[] bArr) {
        String str;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            str = "[SYNC_DATA] handleHealthSportV3Data, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleHealthSportV3Data, jsonData=" + strD);
            HealthSportV3 healthSportV3 = (HealthSportV3) com.ido.ble.common.k.c(strD, HealthSportV3.class);
            if (healthSportV3 == null) {
                str = "[SYNC_DATA] handleHealthSportV3Data, healthSportV3 is null";
            } else {
                if (healthSportV3.year != 0) {
                    if (("" + healthSportV3.year).length() == 2) {
                        healthSportV3.year += 2000;
                    }
                    SyncV3CallBack.onGetHealthSportV3Data(healthSportV3);
                    return;
                }
                str = "[SYNC_DATA] handleHealthSportV3Data, year is 0";
            }
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, str);
    }

    public static void handleIntResult(int i, int i2, int i3) {
        if (i != 18) {
            if (i != 19) {
                return;
            }
            SyncV3CallBack.onSyncV3HealthProgress(i3);
        } else {
            if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i2)) {
                LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] sync v3_health data success!");
                SyncV3CallBack.onSyncV3HealthSuccess();
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] sync v3_health data failed!, error=" + i2);
            SyncV3CallBack.onSyncV3HealthFailed();
        }
    }

    public static void handleJsonResult(int i, byte[] bArr, int i2) {
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.n4 /* 7001 */:
                saveHealthSpO2Data(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.m4 /* 7002 */:
                saveHealthPressureData(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.o4 /* 7003 */:
                saveHealthHRSecondData(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.p4 /* 7004 */:
                handleActivityV3Data(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.q4 /* 7005 */:
                handleHealthGpsV3Data(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.r4 /* 7006 */:
                saveHealthSwimmingData(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.s4 /* 7007 */:
                handleHealthSleepV3Data(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.t4 /* 7008 */:
                handleHealthSportV3Data(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.u4 /* 7009 */:
                handleNoiseData(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.v4 /* 7010 */:
                handleTemperatureData(bArr);
                break;
            case com.veryfit.multi.nativeprotocol.b.w4 /* 7011 */:
                handleBloodPressureData(bArr);
                break;
        }
    }

    private static void handleNoiseData(byte[] bArr) {
        String str;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            str = "[SYNC_DATA] handleNoiseData, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleNoiseData, jsonData=" + strD);
            HealthNoise healthNoise = (HealthNoise) com.ido.ble.common.k.c(strD, HealthNoise.class);
            if (healthNoise != null) {
                SyncV3CallBack.onGetHealthNoiseData(healthNoise);
                return;
            }
            str = "[SYNC_DATA] handleNoiseData, healthNoiseData is null";
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, str);
    }

    private static void handleTemperatureData(byte[] bArr) {
        String str;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            str = "[SYNC_DATA] handleTemperatureData, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] handleTemperatureData, jsonData=" + strD);
            HealthTemperature healthTemperature = (HealthTemperature) com.ido.ble.common.k.c(strD, HealthTemperature.class);
            if (healthTemperature != null) {
                SyncV3CallBack.onGetHealthTemperature(healthTemperature);
                return;
            }
            str = "[SYNC_DATA] handleTemperatureData, TemperatureData is null";
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, str);
    }

    static boolean isSyncV3Type(int i) {
        if (i == 18 || i == 19) {
            return true;
        }
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.n4 /* 7001 */:
            case com.veryfit.multi.nativeprotocol.b.m4 /* 7002 */:
            case com.veryfit.multi.nativeprotocol.b.o4 /* 7003 */:
            case com.veryfit.multi.nativeprotocol.b.p4 /* 7004 */:
            case com.veryfit.multi.nativeprotocol.b.q4 /* 7005 */:
            case com.veryfit.multi.nativeprotocol.b.r4 /* 7006 */:
            case com.veryfit.multi.nativeprotocol.b.s4 /* 7007 */:
            case com.veryfit.multi.nativeprotocol.b.t4 /* 7008 */:
            case com.veryfit.multi.nativeprotocol.b.u4 /* 7009 */:
            case com.veryfit.multi.nativeprotocol.b.v4 /* 7010 */:
            case com.veryfit.multi.nativeprotocol.b.w4 /* 7011 */:
                return true;
            default:
                return false;
        }
    }

    private static void saveHealthHRSecondData(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthHRSecondData, jsonString is null");
            return;
        }
        HealthHeartRateSecond healthHeartRateSecond = (HealthHeartRateSecond) com.ido.ble.common.k.c(strD, HealthHeartRateSecond.class);
        if (healthHeartRateSecond == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthHRSecondData, healthHeartRateSecond is null");
            return;
        }
        if (healthHeartRateSecond.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthHRSecondData, year is 0");
            return;
        }
        List<HealthHeartRateSecondItem> list = healthHeartRateSecond.items;
        if (list == null || list.size() == 0) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthHRSecondData, item is null or size is 0");
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthHRSecondData, jsonData=" + strD);
        healthHeartRateSecond.setDate(TimeUtil.getDate(healthHeartRateSecond.getYear(), healthHeartRateSecond.getMonth(), healthHeartRateSecond.getDay()));
        if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
            com.ido.ble.f.a.b.d().a(healthHeartRateSecond);
        }
        SyncV3CallBack.onGetHealthHeartRateSecondData(healthHeartRateSecond, false);
    }

    private static void saveHealthPressureData(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthPressureData, jsonString is null");
            return;
        }
        HealthPressureWrapper healthPressureWrapper = (HealthPressureWrapper) com.ido.ble.common.k.c(strD, HealthPressureWrapper.class);
        if (healthPressureWrapper == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthPressureData, healthSpO2Wrapper is null");
            return;
        }
        if (healthPressureWrapper.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthPressureData, year is 0");
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthPressureData, jsonData=" + strD);
        Date date = TimeUtil.getDate(healthPressureWrapper.getYear(), healthPressureWrapper.getMonth(), healthPressureWrapper.getDay());
        healthPressureWrapper.setDate(date);
        if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
            com.ido.ble.f.a.b.d().a(healthPressureWrapper);
        }
        List<HealthPressureItem> list = healthPressureWrapper.items;
        if (list == null || list.size() == 0) {
            SyncV3CallBack.onGetHealthPressureData(healthPressureWrapper, null, true);
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthPressureData, item size is 0");
            return;
        }
        for (HealthPressureItem healthPressureItem : healthPressureWrapper.items) {
            healthPressureItem.setYear(healthPressureWrapper.getYear());
            healthPressureItem.setMonth(healthPressureWrapper.getMonth());
            healthPressureItem.setDay(healthPressureWrapper.getDay());
            healthPressureItem.setDate(date);
        }
        if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
            com.ido.ble.f.a.b.d().c(healthPressureWrapper.items);
        }
        SyncV3CallBack.onGetHealthPressureData(healthPressureWrapper, healthPressureWrapper.items, true);
    }

    private static void saveHealthSpO2Data(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthSpO2Data, jsonString is null");
            return;
        }
        HealthSpO2Wrapper healthSpO2Wrapper = (HealthSpO2Wrapper) com.ido.ble.common.k.c(strD, HealthSpO2Wrapper.class);
        if (healthSpO2Wrapper == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthSpO2Data, healthSpO2Wrapper is null");
            return;
        }
        if (healthSpO2Wrapper.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthSpO2Data, year is 0");
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthSpO2Data, jsonData=" + strD);
        Date date = TimeUtil.getDate(healthSpO2Wrapper.getYear(), healthSpO2Wrapper.getMonth(), healthSpO2Wrapper.getDay());
        healthSpO2Wrapper.setDate(date);
        if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
            com.ido.ble.f.a.b.d().a(healthSpO2Wrapper);
        }
        List<HealthSpO2Item> list = healthSpO2Wrapper.items;
        if (list == null || list.size() == 0) {
            SyncV3CallBack.onGetHealthSpO2Data(healthSpO2Wrapper, null, true);
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthSpO2Data, item size is 0");
            return;
        }
        for (HealthSpO2Item healthSpO2Item : healthSpO2Wrapper.items) {
            healthSpO2Item.setYear(healthSpO2Wrapper.getYear());
            healthSpO2Item.setMonth(healthSpO2Wrapper.getMonth());
            healthSpO2Item.setDay(healthSpO2Wrapper.getDay());
            healthSpO2Item.setDate(date);
        }
        if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
            com.ido.ble.f.a.b.d().e(healthSpO2Wrapper.items);
        }
        SyncV3CallBack.onGetHealthSpO2Data(healthSpO2Wrapper, healthSpO2Wrapper.items, true);
    }

    private static void saveHealthSwimmingData(byte[] bArr) {
        String str;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            str = "[SYNC_DATA] saveHealthSwimmingData, jsonString is null";
        } else {
            HealthSwimming healthSwimming = (HealthSwimming) com.ido.ble.common.k.c(strD, HealthSwimming.class);
            if (healthSwimming == null) {
                str = "[SYNC_DATA] saveHealthSwimmingData, healthSwimming is null";
            } else {
                if (healthSwimming.getYear() != 0) {
                    LogTool.d(com.ido.ble.logs.a.f4633a, "[SYNC_DATA] saveHealthSwimmingData, jsonData=" + strD);
                    healthSwimming.setDate(TimeUtil.getDate(healthSwimming.getYear(), healthSwimming.getMonth(), healthSwimming.getDay(), healthSwimming.getHour(), healthSwimming.getMinute(), healthSwimming.getSecond()));
                    if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                        com.ido.ble.f.a.b.d().a(healthSwimming);
                    }
                    SyncV3CallBack.onGetHealthSwimmingData(healthSwimming);
                    return;
                }
                str = "[SYNC_DATA] saveHealthSwimmingData, year is 0";
            }
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, str);
    }
}