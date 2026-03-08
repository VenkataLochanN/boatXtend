package com.ido.common.utils;

import android.app.ActivityManager;
import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class MemoryManagerUtil {
    public static int BIT_NUM = 4;
    public static int HEIGHT = 320;
    private static final long SURPLUS_MEMORY = 3072;
    public static int WIDTH = 360;

    public static boolean getSurplusMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return (memoryInfo.availMem - SURPLUS_MEMORY) - ((long) ((WIDTH * HEIGHT) * BIT_NUM)) > 0;
    }
}