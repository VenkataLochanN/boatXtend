package com.ido.life.util;

import android.app.ActivityManager;
import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class ServiceUtils {
    public static boolean isServiceRunning(Context context, String str) {
        if (!"".equals(str) && str != null) {
            ArrayList arrayList = (ArrayList) ((ActivityManager) context.getSystemService("activity")).getRunningServices(30);
            for (int i = 0; i < arrayList.size(); i++) {
                if (((ActivityManager.RunningServiceInfo) arrayList.get(i)).service.getClassName().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}