package com.ido.life.module.user.set.data;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.common.utils.TimeUtil;
import java.util.Date;
import java.util.Random;

/* JADX INFO: loaded from: classes3.dex */
public class TestDataHelper {
    private static final String TAG = "TestDataHelper";

    private static void insetHealthSpO2(int i, int i2, int i3) {
        Random random = new Random();
        HealthSpO2 healthSpO2 = new HealthSpO2();
        healthSpO2.setDate(new Date(i - 1900, i2 - 1, i3, 0, 0, 0));
        healthSpO2.setYear(i);
        healthSpO2.setMonth(i2);
        healthSpO2.setDay(i3);
        healthSpO2.setStartTime(random.nextInt(180));
        for (int i4 = 0; i4 < 20; i4++) {
            HealthSpO2Item healthSpO2Item = new HealthSpO2Item();
            healthSpO2Item.setDate(healthSpO2.getDate());
            healthSpO2Item.setYear(i);
            healthSpO2Item.setMonth(i2);
            healthSpO2Item.setDay(i3);
            healthSpO2Item.setOffset(random.nextInt(5) + 10);
            healthSpO2Item.setValue(random.nextInt(20) + 80);
        }
    }

    public static void main(String[] strArr) {
        System.out.println("main: " + TimeUtil.now() + AppInfo.DELIM + TimeUtil.getTodayHours());
    }
}